package com.training.finalEval.domain.dto.request

import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import kotlin.math.max

data class reqGetBrandDto (
    @field:Pattern(regexp = "[^&+,:;=?@#|'<>.{}\$^*()%!]*", message = "Invalid input")
    @field:Size(max=10, message="Name max 100 character")
    val search: String

)