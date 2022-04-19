package io.github.mountainest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/info")
    public String get() {
        return "helloworld";
    }
}
