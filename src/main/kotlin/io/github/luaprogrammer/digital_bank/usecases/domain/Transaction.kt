package io.github.luaprogrammer.digital_bank.usecases.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity(name ="transaction")
data class Transaction (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Enumerated(EnumType.STRING)
    val type: TransactionType,

    @Column
    val amount: BigDecimal,

    @Column
    val origin: String? = null,

    @Column
    val destination: String? = null,

    @Column
    val accountNumber: String? = null
)