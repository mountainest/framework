package com.mountain.framework.controller;

import com.mountain.framework.dto.ReqDto;
import com.mountain.framework.utils.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidController {
    @PostMapping("/test")
    public Response testPost(@RequestBody ReqDto req) {
        return Response.success();
    }
}
