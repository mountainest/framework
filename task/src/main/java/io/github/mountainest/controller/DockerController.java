package io.github.mountainest.controller;

import io.github.mountainest.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/docker")
@RestController
public class DockerController {
    @GetMapping("/test")
    public Response<Void> test() {
        return Response.success("Hello world!");
    }
}
