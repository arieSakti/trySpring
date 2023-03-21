package com.training.finalEval.controller

import com.training.finalEval.domain.dto.request.reqBrandDto
import com.training.finalEval.domain.dto.request.reqGetBrandDto
import com.training.finalEval.domain.dto.respon.globalResponse
import com.training.finalEval.repository.userRepository
import com.training.finalEval.service.brandService
import com.training.finalEval.util.jwtGenerator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid


@RestController
@RequestMapping("/v1/brand")
class brandController (val brandService: brandService ) {

    @PostMapping("/createbrand")
    fun create(@Valid @RequestBody request: reqBrandDto): globalResponse<Any> {
        println(request.cdBrand)
        println(request.descBrand)

        brandService.createBrand(request)
        return globalResponse(out_stat = "T", out_mess = "SIGN UP SUCCESS", out_data = "")

    }


//    @PostMapping("/getbrand")
//    fun getBrand (@RequestBody body: reqGetBrandDto, response: HttpServletResponse, errors: Errors): ResponseEntity<Any> {
//        val brand = brandService.findWithFilter(body)
//
//        return ResponseEntity.ok(globalResponse(
//            out_stat = "T",
//            out_mess = "Success.",
//            out_data = brand
//        ))
//    }

    @PostMapping("/getbrand")
    fun getBrand (@Valid @RequestBody body: reqGetBrandDto): ResponseEntity<Any> {

        val brand = brandService.findWithFilter(body)
//        val jwtValue = jwtGenerator.decode
//        val apikey = APIKey

        return ResponseEntity.ok(globalResponse(
            out_stat = "T",
            out_mess = "Success.",
            out_data = brand
        ))
//        if(apikey == null || apikey == ""){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(globalResponse(
//                out_stat = "F",
//                out_mess = "You dont have permission.",
//                out_data = ""
//            ))
//        }
//
//        val foundApiKey = userRepository.findByApiKey()
//        if(!foundApiKey.isPresent){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(globalResponse(
//                out_stat = "F",
//                out_mess = "You dont have permission.",
//                out_data = ""
//            ))
//        }

//        if (apikey == null || apikey == "" || apikey != "1234567890"){
//            return ResponseEntity.ok(globalResponse(
//                out_stat = "F",
//                out_mess = "You Don't Have Permission",
//                out_data = ""
//            ))
//        }


        }


    }
