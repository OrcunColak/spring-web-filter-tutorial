package com.colak.springwebfiltertutorial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/processingTimeService")
public class HelloController {

    // http://localhost:8080/api/v1/processingTimeService/hello
    @GetMapping(path = "hello")
    String greet() {
        return "Hello";
    }
}
