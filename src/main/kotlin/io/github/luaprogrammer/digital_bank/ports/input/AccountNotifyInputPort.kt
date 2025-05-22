package io.github.luaprogrammer.digital_bank.ports.input

import io.github.luaprogrammer.digital_bank.adapters.output.producer.event.AccountEvent

interface AccountNotifyInputPort {
    fun notifyAccount(account: AccountEvent)
}
