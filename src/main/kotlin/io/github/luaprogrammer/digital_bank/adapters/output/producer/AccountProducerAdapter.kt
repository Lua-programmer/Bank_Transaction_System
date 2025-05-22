package io.github.luaprogrammer.digital_bank.adapters.output.producer

import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.AccountEvent
import io.github.luaprogrammer.digital_bank.ports.output.AccountProducerOutputPort
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class AccountProducerAdapter(
    private val kafkaTemplate: KafkaTemplate<String, AccountEvent>
): AccountProducerOutputPort {

    private val logger: Logger = LoggerFactory.getLogger(AccountProducerAdapter::class.java)

    companion object {
        private const val TOPIC = "account-topic"
    }

    override fun sendMessage(event: AccountEvent) {
        kafkaTemplate.send(TOPIC, event)
        logger.info("Mensagem enviada ao produtor da conta: $event")
    }
}