package com.training.finalEval.service.impl

import com.training.finalEval.domain.dto.request.reqBrandDto
import com.training.finalEval.domain.dto.request.reqGetBrandDto
import com.training.finalEval.domain.entity.brandEntity
import com.training.finalEval.repository.brandRepository
import com.training.finalEval.service.brandService
import org.springframework.stereotype.Service


@Service
class brandServiceImpl (val brandRepository: brandRepository):brandService {
    override fun createBrand(request: reqBrandDto) {

        val brand : brandEntity=brandEntity(
        cdBrand = request.cdBrand,
        descBrand = request.descBrand
        )

        brandRepository.save(brand)
    }

    override fun getAllData(): List<brandEntity> {
        val data = brandRepository.getAllData()

        return data
    }

    override fun findWithFilter(body: reqGetBrandDto): List<brandEntity> {
       // validationUtils.validate(body)
        val found = brandRepository.findWithFilter(body.search.toString())

        println(found)

        return found

    }
}