package com.example.demo.payment.adapter.`in`.web.view

import com.example.demo.payment.adapter.`in`.web.common.WebAdapter
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import reactor.core.publisher.Mono

@WebAdapter
@Controller
class CheckoutController {

    @GetMapping("/")
    fun checkout(): Mono<String> {
        return Mono.just("checkout")
    }
}