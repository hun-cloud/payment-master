package com.example.demo.payment.application.test

import com.example.demo.payment.domain.PaymentEvent
import reactor.core.publisher.Mono

interface PaymentDatabaseHelper {

    fun getPayments(orderId: String): PaymentEvent?

    fun clean(): Mono<Void>
}