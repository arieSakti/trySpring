package com.training.finalEval.domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import java.util.*
import javax.persistence.*


@Entity
@Table(name = "users")
data class userEntity (


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "nama", length = 100, columnDefinition = "VARCHAR")
    val nama: String,

    @Column(name = "email", length = 100, columnDefinition = "VARCHAR")
    val email : String = "placeholder@mail.com",

    @Column(name = "password", length = 100, columnDefinition = "VARCHAR")
    val password: String,

    @Column(name = "api_key", columnDefinition = "CHAR(36)")
    var apiKey: String,


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "createdAt", nullable = false)
    val createdAt: Date?=null,

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "updatedAt")
    val updatedAt: Date? = null

)