package io.github.luaprogrammer.digital_bank.usecases.domain

import jakarta.persistence.*

@Entity(name = "person")
data class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int?,

    @Column
    val name: String,

    @Column(unique = true)
    val cpf: String,

    @Column(unique = true)
    val email: String,

    )
