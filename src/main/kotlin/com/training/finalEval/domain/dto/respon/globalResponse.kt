package com.training.finalEval.domain.dto.respon

class globalResponse <T>(
    val out_stat: String,
    val out_mess: String,
    val out_data: T?
)
