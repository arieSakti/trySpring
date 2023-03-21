package com.training.finalEval.domain.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "brand_unit")
data class brandEntity (

    @Id
    @Column(name = "CD_BRAND", length = 100, columnDefinition = "VARCHAR")
    val cdBrand: String,

    @Column(name = "DESC_BRAND", length = 100, columnDefinition = "VARCHAR")
    val descBrand: String
)

