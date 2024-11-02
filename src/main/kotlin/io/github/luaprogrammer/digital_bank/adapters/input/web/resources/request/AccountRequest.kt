package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request

import java.math.BigDecimal

data class AccountRequest(

    val accountNumber: String?,

    var balance: BigDecimal? = BigDecimal.ZERO,

    val person: PersonRequest
)
