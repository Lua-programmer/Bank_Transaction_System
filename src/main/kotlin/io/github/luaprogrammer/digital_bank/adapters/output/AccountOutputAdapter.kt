package io.github.luaprogrammer.digital_bank.adapters.output

import io.github.luaprogrammer.digital_bank.adapters.output.exceptions.AccountNotFoundException
import io.github.luaprogrammer.digital_bank.adapters.output.repository.AccountRepository
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountOutputAdapter(
    private val accountRepository: AccountRepository
) : AccountOutputPort {

    private val logger: Logger = LoggerFactory.getLogger(AccountOutputAdapter::class.java)

    override fun saveAccount(account: Account) {
        accountRepository.save(account)
        logger.info("Conta salva com número: ${account.accountNumber}")
    }

    override fun getAccount(accountNumber: String): Account =
        accountRepository.findByAccountNumber(accountNumber)
            ?: throw AccountNotFoundException("Conta não encontrada: $accountNumber")
}
