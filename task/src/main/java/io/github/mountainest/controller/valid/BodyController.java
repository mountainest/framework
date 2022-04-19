package io.github.mountainest.controller.valid;

import io.github.mountainest.utils.Response;
import javax.validation.groups.Default;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * json参数校验
 */
@RestController
public class BodyController {
    @PostMapping("/test")
    public Response testPost(@Validated @RequestBody ReqDto req) {
        return Response.success();
    }

    @PutMapping("/test")
    public Response testPut(@Validated({Default.class, ReqDto.Update.class}) @RequestBody ReqDto req) {
        return Response.success();
    }
}
