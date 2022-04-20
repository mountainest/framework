package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.po.DeptPo;
import io.github.mountainest.service.IDeptService;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "部门管理")
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Resource
    private IDeptService deptService;

    @PostMapping
    public Result<Void> add(@Validated @RequestBody DeptPo po) {
        this.deptService.save(po);
        return Result.success();
    }
}
