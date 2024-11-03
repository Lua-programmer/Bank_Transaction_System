package io.github.luaprogrammer.digital_bank.usecases

import io.github.luaprogrammer.digital_bank.adapters.output.exceptions.AccountNotFoundException
import io.github.luaprogrammer.digital_bank.ports.exceptions.InsufficientBalanceException
import io.github.luaprogrammer.digital_bank.ports.output.AccountOutputPort
import io.github.luaprogrammer.digital_bank.ports.output.TransactionOutputPort
import io.github.luaprogrammer.digital_bank.usecases.domain.Account
import io.github.luaprogrammer.digital_bank.usecases.domain.Person
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.math.BigDecimal
import kotlin.test.assertFailsWith

@ExtendWith(MockKExtension::class)
class TransactionUseCaseTest {
    private lateinit var transactionUseCase: TransactionUseCase
    private val accountOutputPort: AccountOutputPort = mockk(relaxed = true)
    private val transactionOutputPort: TransactionOutputPort = mockk(relaxed = true)

    @BeforeEach
    fun setUp() {
        transactionUseCase = TransactionUseCase(accountOutputPort, transactionOutputPort)

        every {
            accountOutputPort.getAccount("123")
        } returns Account(
            id = 1,
            balance = BigDecimal("100.00"),
            accountNumber = "123",
            person = Person(
                id = 1,
                name = "John Doe",
                cpf = "485.833.240-34",
                email = "john.doe@example.com"
            )
        )
        every {
            accountOutputPort.getAccount("456")
        } returns Account(
            id = 2,
            balance = BigDecimal("0.00"),
            accountNumber = "456",
            person = Person(
                id = 2,
                name = "Jane Doe",
                cpf = "313.123.456-78",
                email = "jane.doe@example.com"
            )
        )

        every {
            accountOutputPort.getAccount("798")
        } returns Account(
            id = 3,
            balance = BigDecimal("0.00"),
            accountNumber = "798",
            person = Person(
                id = 3,
                name = "Alice Doe",
                cpf = "123.456.789-01",
                email = "alice.doe@example.com"
            )
        )
    }

    @Test
    fun `test deposit`() {
        transactionUseCase.deposit("100.00", "123")

        verify {
            accountOutputPort.saveAccount(
                Account(
                    id = 1,
                    balance = BigDecimal("200.00"),
                    accountNumber = "123",
                    person = Person(
                        id = 1,
                        name = "John Doe",
                        cpf = "485.833.240-34",
                        email = "john.doe@example.com"
                    )
                )
            )
        }

    }

    @Test
    fun `test withdrawal`() {
        transactionUseCase.withdraw("50.00", "123")
        verify {
            accountOutputPort.saveAccount(
                Account(
                    id = 1,
                    balance = BigDecimal("50.00"),
                    accountNumber = "123",
                    person = Person(
                        id = 1,
                        name = "John Doe",
                        cpf = "485.833.240-34",
                        email = "john.doe@example.com"
                    )
                )
            )
        }
    }

    @Test
    fun `test transfer`() {
        transactionUseCase.transfer("30.00", "123", "456")
        verify {
            accountOutputPort.saveAccount(
                Account(
                    id = 1,
                    balance = BigDecimal("70.00"),
                    accountNumber = "123",
                    person = Person(
                        id = 1,
                        name = "John Doe",
                        cpf = "485.833.240-34",
                        email = "john.doe@example.com"
                    )
                )
            )
        }
        verify {
            accountOutputPort.saveAccount(
                Account(
                    id = 2,
                    balance = BigDecimal("30.00"),
                    accountNumber = "456",
                    person = Person(
                        id = 2,
                        name = "Jane Doe",
                        cpf = "313.123.456-78",
                        email = "jane.doe@example.com"
                    )
                )
            )
        }
    }

    @Test
    fun `test insufficient balance on withdrawal`() {
        assertFailsWith<InsufficientBalanceException>("Saldo Insuficiente") {
            transactionUseCase.withdraw("150.00", "123")
        }
    }

    @Test
    fun `test account not found`() {
        every {
            accountOutputPort.getAccount("999")
        } throws AccountNotFoundException("Conta não encontrada")

        assertFailsWith<AccountNotFoundException>("Account not found") {
            transactionUseCase.withdraw("50.00", "999")

        }
    }
}
