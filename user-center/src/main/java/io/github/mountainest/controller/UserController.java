package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.dto.UserDto;
import io.github.mountainest.po.UserPo;
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
    public Result save(@Validated @RequestBody UserPo po) {
        this.userService.save(po);
        return Result.success();
    }

    @ApiOperation("查询用户列表")
    @GetMapping()
    public Result<List<UserDto>> list() {
        List<UserDto> dtoList = this.userService.list();
        return Result.success(dtoList);
    }
}
