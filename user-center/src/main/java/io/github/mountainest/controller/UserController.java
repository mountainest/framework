package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.dto.UserDto;
import io.github.mountainest.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户管理")
@RequestMapping("/users")
@RestController
public class UserController {
    @Resource
    private IUserService userService;

    @ApiOperation("新增用户")
    @PostMapping()
    public Result save(@Validated @RequestBody UserDto dto) {
        this.userService.save(dto);
        return Result.success();
    }

    @ApiOperation("查询单个用户信息")
    @GetMapping("/{id}")
    public Result<UserDto> list(@PathVariable("id") Long id) {
        return Result.success(this.userService.getById(id));
    }

    @ApiOperation("查询用户列表")
    @GetMapping()
    public Result<List<UserDto>> list() {
        List<UserDto> dtoList = this.userService.list();
        return Result.success(dtoList);
    }
}
