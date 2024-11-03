package io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response

import com.fasterxml.jackson.annotation.JsonInclude
import io.github.luaprogrammer.digital_bank.usecases.domain.TransactionType
import java.math.BigDecimal

data class TransactionResponse(

    val type: TransactionType,

    val amount: BigDecimal,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val origin: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val destination: String? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val accountNumber: String? = null
)