package com.example.demo.payment.application.port.out

import com.example.demo.payment.domain.PaymentEvent
import reactor.core.publisher.Mono

interface SavePaymentPort {

    fun save(paymentEvent: PaymentEvent): Mono<Void>
}