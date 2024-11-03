package io.github.luaprogrammer.digital_bank.usecases.domain

enum class TransactionType(name: String) {
    T("transfer"),
    D("deposit"),
    W("withdrawal");
    override fun toString(): String = name;
}