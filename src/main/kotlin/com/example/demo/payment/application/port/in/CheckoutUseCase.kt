package com.example.demo.payment.application.port.`in`

import com.example.demo.payment.domain.CheckoutResult
import reactor.core.publisher.Mono

interface CheckoutUseCase {

   fun checkout(command: CheckoutCommand): Mono<CheckoutResult>
}