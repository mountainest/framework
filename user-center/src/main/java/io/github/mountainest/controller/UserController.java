package io.github.mountainest.controller;

import io.github.mountainest.po.UserPo;
import io.github.mountainest.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "用户管理")
@RequestMapping("/users")
@RestController
public class UserController {
    @Resource
    private IUserService userService;

    @ApiOperation("新增用户")
    @PostMapping()
    public void add(@RequestBody UserPo po) {
        this.userService.add(po);
    }
}
