package com.example.demo.payment.adapter.out.persistent

import com.example.demo.common.PersistentAdapter
import com.example.demo.payment.adapter.out.persistent.repository.PaymentRepository
import com.example.demo.payment.application.port.out.SavePaymentPort
import com.example.demo.payment.domain.PaymentEvent
import reactor.core.publisher.Mono

@PersistentAdapter
class PaymentPersistentAdapter (
    private val paymentRepository: PaymentRepository
): SavePaymentPort {

    override fun save(paymentEvent: PaymentEvent): Mono<Void> {
        return paymentRepository.save(paymentEvent)
    }
}