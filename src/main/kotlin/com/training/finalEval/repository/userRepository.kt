package com.training.finalEval.repository

import com.training.finalEval.domain.entity.userEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface userRepository : JpaRepository<userEntity, Long> {
    fun findByEmail(email: String): Optional<userEntity>

    fun findByApiKey(apiKey: String): Optional<userEntity>




}