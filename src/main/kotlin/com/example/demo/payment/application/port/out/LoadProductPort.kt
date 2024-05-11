package com.example.demo.payment.application.port.out

import com.example.demo.payment.domain.Product
import reactor.core.publisher.Flux

interface LoadProductPort {

    fun getProducts(cartId: Long, productIds: List<Long>): Flux<Product>
}