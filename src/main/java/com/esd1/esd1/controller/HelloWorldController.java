package com.esd1.esd1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController  // Marks this class as a REST controller
public class HelloWorldController {

    @GetMapping("/hello")  // Defines a GET endpoint at "/hello"
    public String sayHello() {
        return "Hello, World!";  // Returns a simple string as response
    }
}
