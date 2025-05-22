package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.adapters.input.web.resources.request.AccountRequest
import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.AccountEvent
import io.github.luaprogrammer.digital_bank.ports.input.AccountNotifyInputPort
import io.github.luaprogrammer.digital_bank.ports.output.AccountProducerOutputPort
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class AccountNotifyUseCase(
    private val accountProducerOutputPort: AccountProducerOutputPort
): AccountNotifyInputPort {

    private val logger: Logger = LoggerFactory.getLogger(AccountNotifyUseCase::class.java)

    override fun notifyAccount(account: AccountEvent) {
        accountProducerOutputPort.sendMessage(account)
        logger.info("Notificação de conta enviada: $account")
    }
}