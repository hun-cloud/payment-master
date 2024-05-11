package com.example.demo.payment.adapter.out.persistent.repository

import com.example.demo.payment.domain.PaymentEvent
import reactor.core.publisher.Mono

interface PaymentRepository {

    fun save(paymentEvent: PaymentEvent): Mono<Void>
}