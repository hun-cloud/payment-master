package com.example.demo.payment.adapter.out.web.product.client

import com.example.demo.payment.domain.Product
import reactor.core.publisher.Flux

interface ProductClient {

    fun getProducts(cartId: Long, productIds: List<Long>): Flux<Product>
}