package com.training.finalEval.domain.dto

import com.training.finalEval.domain.dto.respon.globalResponse
import com.training.finalEval.error.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class RespExecptionHandler {


    @ExceptionHandler(value =[UnauthorizedException::class])
    fun unauthorized(unauthorizedException: UnauthorizedException): ResponseEntity<Any> {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(globalResponse("F", "You Don't Have Permission To Access API", ""))
    }

}