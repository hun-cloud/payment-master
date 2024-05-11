package com.example.demo.payment.application.service

import com.example.demo.payment.application.port.`in`.CheckoutCommand
import com.example.demo.payment.application.port.`in`.CheckoutUseCase
import com.example.demo.payment.application.test.PaymentDatabaseHelper
import com.example.demo.payment.application.test.PaymentTestConfiguration
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import reactor.test.StepVerifier
import java.util.UUID

@SpringBootTest
@Import(PaymentTestConfiguration::class)
class CheckoutServiceTest (
    @Autowired private val checkoutUseCase: CheckoutUseCase,
    @Autowired private val paymentDatabaseHelper: PaymentDatabaseHelper
){
    @BeforeEach
    fun setUp() {
        paymentDatabaseHelper.clean().block()
    }

    @Test
    fun `should save paymentEvent and PaymentOrder successfully`() {
        val orderId = UUID.randomUUID().toString()
        val checkoutCommand = CheckoutCommand(
            cartId = 1,
            buyerId = 1,
            productIds = listOf(1, 2, 3),
            idempotencyKey = orderId
        )

        StepVerifier.create(checkoutUseCase.checkout(checkoutCommand))
            .expectNextMatches {
                it.amount.toInt() == 60000 && it.orderId == orderId
            }
            .verifyComplete()

        val paymentEvent = paymentDatabaseHelper.getPayments(orderId)!!

        assertThat(paymentEvent.orderId).isEqualTo(orderId)
        assertThat(paymentEvent.totalAmount()).isEqualTo(60000)
        assertThat(paymentEvent.paymentOrders.size).isEqualTo(checkoutCommand.productIds.size)
        assertFalse(paymentEvent.isPaymentDone())
        assertTrue(paymentEvent.paymentOrders.all { !it.isLedgerUpdated() })
        assertTrue(paymentEvent.paymentOrders.all { !it.isWalletUpdated() })
    }
}