package io.github.luaprogrammer.digital_bank.adapters.input.web.resources

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.constants.TRANSACTION_RESOURCE_V1_URI
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper.toTransactionsResponse
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AmountRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.TransferRequest
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.response.TransactionsResponse
import io.github.luaprogrammer.digital_bank.ports.input.TransactionInputPort
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(TRANSACTION_RESOURCE_V1_URI)
class TransactionResource(
    private val transactionInputPort: TransactionInputPort
) {

    private val logger: Logger = LoggerFactory.getLogger(TransactionResource::class.java)

    @PostMapping("deposit")
    fun deposit(
        @RequestBody @Valid request: AmountRequest
    ) {
        logger.info("Iniciando depósito na conta ${request.accountNumber}")
        transactionInputPort.deposit(
            request.amount,
            request.accountNumber
        )
        logger.info("Depósito realizado com sucesso na conta ${request.accountNumber}")
    }

    @PostMapping("withdraw")
    fun withdraw(
        @RequestBody @Valid request: AmountRequest
    ) {
        logger.info("Iniciando saque na conta ${request.accountNumber}")
        transactionInputPort.withdraw(
            request.amount,
            request.accountNumber
        )
        logger.info("Saque realizado com sucesso na conta ${request.accountNumber}")
    }

    @PostMapping("transfer")
    fun transfer(
        @RequestBody request: TransferRequest
    ) {
        logger.info("Iniciando transferência de ${request.amount} da conta ${request.accountOrigin} para a conta ${request.accountDestination}")
        transactionInputPort.transfer(
            request.amount,
            request.accountOrigin,
            request.accountDestination
        )
        logger.info("Transferência realizada com sucesso de ${request.amount} da conta ${request.accountOrigin} para a conta ${request.accountDestination}")
    }

    @GetMapping
    fun transactions(): TransactionsResponse =
        transactionInputPort.transactions().toTransactionsResponse()
}
