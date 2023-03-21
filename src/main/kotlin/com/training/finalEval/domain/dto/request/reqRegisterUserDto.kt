package com.training.finalEval.domain.dto.request

import javax.validation.constraints.*

data class reqRegisterUserDto (

    @field:NotEmpty(message = "Name cannot be blank")
    @field:Pattern(regexp = "[^&+,:;=?@#|'<>.{}$^*()%!]*", message = "Special character found, access now allowed")
    @field:Size(max=100, message="Name max 100 character")
    val name: String,

    @field:NotEmpty(message = "Email cannot be blank")
    @field:Email(message = "Wrong format email")
    @field:Size(max=100, message = "Email max 100 character")
    val email: String,

    @field:NotNull(message = "Password cannot be null")
    val password: String

    )
