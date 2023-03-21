package com.training.finalEval.repository

import com.training.finalEval.domain.entity.brandEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface brandRepository  : JpaRepository<brandEntity, Long> {

    @Query(value="SELECT B.* FROM brand_unit B", nativeQuery = true)
    fun getAllData():List<brandEntity>

    @Query(value = "SELECT B.* FROM brand_unit B WHERE B.desc_brand LIKE %:search%", nativeQuery = true)
    fun findWithFilter(@Param("search") search: String): List<brandEntity>


}