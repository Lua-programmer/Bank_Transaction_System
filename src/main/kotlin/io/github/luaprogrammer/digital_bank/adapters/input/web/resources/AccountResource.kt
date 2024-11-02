package io.github.luaprogrammer.digital_bank.adapters.input.web.resources

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.constants.ACCOUNT_RESOURCE_V1_URI
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper.toAccount
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.mapper.toAccountResponse
import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AccountRequest
import io.github.luaprogrammer.digital_bank.ports.input.AccountInputPort
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(ACCOUNT_RESOURCE_V1_URI)
class AccountResource(

    private val accountInputPort: AccountInputPort
) {

    @RequestMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun createAccount(
        @RequestBody @Valid request: AccountRequest
    ) = accountInputPort.createAccount(request.toAccount())

    @GetMapping("find")
    fun getAccount(
        @RequestParam accountNumber: String
    ) = accountInputPort.getAccount(accountNumber)!!.toAccountResponse()
}
