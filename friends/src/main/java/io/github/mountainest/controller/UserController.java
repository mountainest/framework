package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.dto.UserDto;
import io.github.mountainest.dto.UserQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户资料")
@RequestMapping("/users")
@RestController
public class UserController {
    @ApiOperation("新增用户资料")
    @PostMapping()
    public Result<Void> add(UserDto dto) {
        return Result.success();
    }

    @ApiOperation("修改用户资料")
    @PutMapping()
    public Result<Void> update(UserDto dto) {
        return Result.success();
    }

    @ApiOperation("查找用户资料")
    @GetMapping()
    public Result<List<UserDto>> list(UserQuery query) {
        return Result.success();
    }
}
