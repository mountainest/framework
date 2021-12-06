package com.mountain.framework.controller.valid;

import com.mountain.framework.dto.ReqDto;
import com.mountain.framework.utils.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BodyController {
    @PostMapping("/test")
    public Response testPost(@Validated @RequestBody ReqDto req) {
        return Response.success();
    }

    @PutMapping("/test")
    public Response testPut(@Validated(ReqDto.Update.class) @RequestBody ReqDto req) {
        return Response.success();
    }
}
