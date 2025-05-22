package io.github.luaprogrammer.digital_bank.adapters.output.producer.event

import java.math.BigDecimal

data class AccountEvent (
    val balance: BigDecimal,
    var accountNumber: String?,
    val person: PersonEvent,
)