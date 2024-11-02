package io.github.luaprogrammer.digital_bank.usecases.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name = "account")
data class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column
    var balance: BigDecimal,

    @Column(unique = true)
    var accountNumber: String?,

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "person_id")
    val person: Person
)
