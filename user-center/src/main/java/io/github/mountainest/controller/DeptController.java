package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.dto.DeptDto;
import io.github.mountainest.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "部门管理")
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Resource
    private IDeptService deptService;

    @ApiOperation("新增部门")
    @PostMapping
    public Result<Void> save(@Validated @RequestBody DeptDto dto) {
        this.deptService.save(dto);
        return Result.success();
    }

    @ApiOperation("查询指定部门")
    @GetMapping("/{id}")
    public Result<DeptDto> query(@PathVariable("id") Long id) {
        return Result.success(this.deptService.get(id));
    }
}
