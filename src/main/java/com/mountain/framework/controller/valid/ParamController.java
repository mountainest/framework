package com.mountain.framework.controller.valid;

import com.mountain.framework.utils.Response;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 简单参数校验
 */
@Validated
@RestController
public class ParamController {
    @GetMapping("/test")
    public Response testGet(@NotNull @RequestParam Long id, @NotBlank String name) {
        return Response.success();
    }
}
