package io.github.luaprogrammer.digital_bank.objects

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AccountRequest
import java.math.BigDecimal

object AccountObject {

    fun createAccountRequest(): AccountRequest =
        AccountRequest(
            accountNumber = "5773-3133-585333-2",
            balance = BigDecimal(100.00),
            person = PersonObject.createPersonRequest()
        )
}