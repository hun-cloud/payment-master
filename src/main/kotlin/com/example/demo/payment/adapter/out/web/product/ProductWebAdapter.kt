package com.example.demo.payment.adapter.out.web.product

import com.example.demo.common.WebAdapter
import com.example.demo.payment.adapter.out.web.product.client.ProductClient
import com.example.demo.payment.application.port.out.LoadProductPort
import com.example.demo.payment.domain.Product
import reactor.core.publisher.Flux

@WebAdapter
class ProductWebAdapter (
    private val productClient: ProductClient
): LoadProductPort {

    override fun getProducts(cartId: Long, productIds: List<Long>): Flux<Product> {
        return productClient.getProducts(cartId, productIds)
    }
}