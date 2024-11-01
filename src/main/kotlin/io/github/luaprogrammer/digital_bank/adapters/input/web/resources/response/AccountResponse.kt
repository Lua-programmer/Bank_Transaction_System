package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response

import java.math.BigDecimal

data class AccountResponse(

    val balance: BigDecimal?,

    val accountNumber: String?,

    val person: PersonResponse
)