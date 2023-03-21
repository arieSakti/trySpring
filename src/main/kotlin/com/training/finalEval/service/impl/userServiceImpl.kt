package com.training.finalEval.service.impl

import com.training.finalEval.domain.dto.request.reqLoginDto
import com.training.finalEval.domain.dto.request.reqRegisterUserDto
import com.training.finalEval.domain.entity.userEntity
import com.training.finalEval.repository.userRepository
import com.training.finalEval.service.userService
import org.springframework.data.crossstore.ChangeSetPersister
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class userServiceImpl(val userRepository: userRepository): userService {
    override fun createUser(request: reqRegisterUserDto) {
        val apiKeyValue = UUID.randomUUID().toString()

        val user:userEntity=userEntity(
            nama = request.name,
            email = request.email,
            password = request.password,
            apiKey = apiKeyValue
        )
        userRepository.save(user)
    }

    override fun loginUser(request: reqLoginDto): Optional<userEntity> {
        val user = userRepository.findByEmail(request.email)

        if(!user.isPresent){
            throw NotFoundException()
        }
        return user
    }

}