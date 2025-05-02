package io.github.luaprogrammer.digital_bank.adapters.input.web.resources

import io.github.luaprogrammer.digital_bank.IntegrationTest
import io.github.luaprogrammer.digital_bank.objects.AmountObject.createDepositRequest
import io.github.luaprogrammer.digital_bank.objects.AmountObject.createTransferRequest
import io.github.luaprogrammer.digital_bank.objects.AmountObject.createWithdrawRequest
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class TransactionResourceTest : IntegrationTest() {

    companion object {
        private const val URI = "/bank-digital/v1/transactions"
    }

    @Test
    fun `Should deposit money successfully`() {
        val deposit = createDepositRequest()

        mockMvc.perform(
            post("$URI/deposit")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(deposit))
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `Should withdraw money successfully`() {
        val withdraw = createWithdrawRequest()

        mockMvc.perform(
            post("$URI/withdraw")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(withdraw))
        )
            .andExpect(status().isOk)
    }

    @Test
    fun `Should transfer money successfully`() {
        val transfer = createTransferRequest()

        mockMvc.perform(
            post("$URI/transfer")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(transfer))
        )
            .andExpect(status().isOk)
    }
}