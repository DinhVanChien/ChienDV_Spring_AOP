package com.chiendv.examplespringaop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAopController {
    @PostMapping("/test")
    public String test(@RequestBody String requestBody) {
        System.out.println("RequestBody: " + requestBody);
        return "Response Test AOP";
    }

    @GetMapping("/test-exception")
    public String testException() {
        System.out.println("Test Exception");
        System.out.println(1/0);
        return "Response Test Exception";
    }
}
