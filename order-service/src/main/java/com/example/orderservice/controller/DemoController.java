package com.example.orderservice.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("/api/order")
public class DemoController {
    private final RestClient restClient;

    private final WebClient webClient;

    public DemoController(RestClient.Builder builder, WebClient.Builder webClient) {
        this.restClient = builder.baseUrl("http://inventory-service").build();
        this.webClient = webClient.baseUrl("http://inventory-service").build();
    }

    @GetMapping("/order-hello")
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallback")
    public String hello(){
        String message =  restClient.get().uri("/api/inventory/hello").retrieve().body(String.class);
        return message + " called from order service!";
    }

    //TODO: FIX WEB CLIENT NOT WORKING
    @GetMapping("/hello")
    @CircuitBreaker(name = "orderService", fallbackMethod = "fallback")
    public String helloReactive(){
        String message =  webClient.get().uri("/api/inventory/hello").retrieve().bodyToMono(String.class).block();
        return message + " called from order service!";
    }

    public String fallback(Throwable throwable){
        System.out.println("FALLBACK METHOD INVOKED!!!!");
        return "Fallback response: - server is unavailable please try again later!";
    }
}
