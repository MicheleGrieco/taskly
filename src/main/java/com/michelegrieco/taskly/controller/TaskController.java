package com.michelegrieco.taskly.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, Spring Boot!";
    }
}
