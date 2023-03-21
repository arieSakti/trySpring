package com.training.finalEval.service

import com.training.finalEval.domain.dto.request.reqLoginDto
import com.training.finalEval.domain.dto.request.reqRegisterUserDto
import com.training.finalEval.domain.entity.userEntity
import java.util.*

interface userService{

    fun createUser(request: reqRegisterUserDto)
    fun loginUser(request: reqLoginDto): Optional<userEntity>

}