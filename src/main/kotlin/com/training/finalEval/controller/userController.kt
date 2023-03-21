package com.training.finalEval.controller

import com.training.finalEval.domain.dto.request.reqLoginDto
import com.training.finalEval.domain.dto.request.reqRegisterUserDto
import com.training.finalEval.domain.dto.respon.globalResponse
import com.training.finalEval.domain.dto.respon.jwtResponse
import com.training.finalEval.domain.dto.respon.resValidate
import com.training.finalEval.error.UnauthorizedException
import com.training.finalEval.repository.userRepository
import com.training.finalEval.service.userService
import com.training.finalEval.util.jwtGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.yaml.snakeyaml.tokens.AliasToken
import javax.validation.Valid




@RestController
@RequestMapping("/v1/user")
class userController (val userService: userService, val userRepository: userRepository, val jwtGenerator: jwtGenerator) {

    @PostMapping("/signup")
    fun exerciseRegister(@Valid @RequestBody request: reqRegisterUserDto): globalResponse<Any> {
        println(request.name)
        println(request.email)
        println(request.password)
        userService.createUser(request)
        return globalResponse("T", "success register", "")
    }

    @PostMapping("/signin")
    fun login(@RequestBody request: reqLoginDto, jwtGenerator: jwtGenerator): ResponseEntity<Any> {
        val user = userService.loginUser(request)

        if (user.get().password != request.password) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                globalResponse(
                    out_stat = "F",
                    out_mess = "Invalid credential",
                    out_data = ""
                )
            )
        }

        val jwt = jwtGenerator.createJWT(
            id = user.get().id.toString(),
            issuer = "http://localhost:8000",
            subjet = user.get().email.toString(),
            ttlMillis = 24 * 60 * 60 * 1000,
            payload = user.get().apiKey
        )


        return ResponseEntity.status(HttpStatus.OK).body(
            globalResponse(
                out_stat = "T",
                out_mess = "Sign In",
                out_data = jwtResponse(jwt)
            )
        )
    }

    @PostMapping("/validation")
    fun validation(@RequestHeader("token") token: String): ResponseEntity<Any>{
        if (token == null || token ==""){
            throw UnauthorizedException()
        }
        val jwtValue = jwtGenerator.decodeJWT(token)
        val foundEmail = userRepository.findByEmail(jwtValue.subject)
        val emailValue = jwtValue.subject


        val apiKey = jwtValue.audience

        if(!foundEmail.isPresent){
            throw UnauthorizedException()
        }
        return ResponseEntity.status(HttpStatus.OK).body(globalResponse("T", "SUCCESS", resValidate(emailValue, apiKey) ))
    }

}


