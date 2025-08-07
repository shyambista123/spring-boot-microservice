package com.example.inventoryservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
public class DemoController {

    @GetMapping("/hello")
    public String greet(){
        return "Hello from inventory service !";
    }
}
