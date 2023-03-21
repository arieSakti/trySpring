package com.training.finalEval.service

import com.training.finalEval.domain.dto.request.reqBrandDto
import com.training.finalEval.domain.dto.request.reqGetBrandDto
import com.training.finalEval.domain.entity.brandEntity

interface brandService {
    fun createBrand (request:reqBrandDto)
    fun getAllData():List<brandEntity>
    fun findWithFilter(body: reqGetBrandDto): List<brandEntity>
}