package io.github.luaprogrammer.digital_bank.adapters.input.consumer

import io.github.luaprogrammer.digital_bank.adapters.input.consumer.mapper.toAccount
import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.AccountEvent
import io.github.luaprogrammer.digital_bank.ports.input.CreateAccountInputPort
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CreateCreateAccountConsumer(
    private val createAccountInputPort: CreateAccountInputPort
) {

    private val logger: org.slf4j.Logger = LoggerFactory.getLogger(CreateCreateAccountConsumer::class.java)

    @KafkaListener(topics = ["account-topic"], groupId = "digital-bank-api")
    fun consume(accountEvent: AccountEvent) {
        logger.info("Evento de conta recebida: $accountEvent")
        createAccountInputPort.createAccount(accountEvent.toAccount())
    }
}