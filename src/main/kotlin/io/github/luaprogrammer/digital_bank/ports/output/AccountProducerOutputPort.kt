package io.github.luaprogrammer.digital_bank.ports.output

import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.AccountEvent

interface AccountProducerOutputPort {
    fun sendMessage(event: AccountEvent)
}