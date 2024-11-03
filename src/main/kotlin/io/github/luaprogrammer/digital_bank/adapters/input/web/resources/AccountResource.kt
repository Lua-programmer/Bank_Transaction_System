package io.github.luaprogrammer.digital_bank.adapters.input.web.resources

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.constants.ACCOUNT_RESOURCE_V1_URI
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper.toAccount
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper.toAccountResponse
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AccountRequest
import io.github.luaprogrammer.digital_bank.ports.input.AccountInputPort
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ACCOUNT_RESOURCE_V1_URI)
class AccountResource(

    private val accountInputPort: AccountInputPort
) {

    private val logger: Logger = LoggerFactory.getLogger(AccountResource::class.java)

    @RequestMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createAccount(
        @RequestBody @Valid request: AccountRequest
    ) {
        logger.info("Iniciando criação de conta... ")
        accountInputPort.createAccount(request.toAccount())
        logger.info("Conta criada com sucesso... ")
    }


    @GetMapping("find")
    fun getAccount(
        @RequestParam accountNumber: String
    ) {
        logger.info("Buscando conta com número $accountNumber... ")
        accountInputPort.getAccount(accountNumber)!!.toAccountResponse()
        logger.info("Conta encontrada com sucesso... ")
    }

}
