package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.ResultException;
import io.github.mountainest.common.ErrCode;
import io.github.mountainest.dto.DeptDto;
import io.github.mountainest.service.IDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.groups.Default;
import java.util.List;

@Api(tags = "部门管理")
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Resource
    private IDeptService deptService;

    @ApiOperation("新增部门")
    @PostMapping
    public Result<Void> save(@Validated({PostMapping.class, Default.class}) @RequestBody DeptDto dto) {
//        this.checkLevel(dto.getLevel(), dto.getDidPath());
        this.deptService.save(dto);
        return Result.success();
    }

    @ApiOperation("删除部门")
    @DeleteMapping("/{id}")
    public Result<Void> delete(@PathVariable("id") Long id) {
        this.checkChildren(id);
        this.deptService.delete(id);
        return Result.success();
    }

    @ApiOperation("修改部门")
    @PutMapping("/{id}")
    public Result<Void> update(@PathVariable("id") Long id,
            @Validated({PutMapping.class, Default.class}) @RequestBody DeptDto dto) {
//        this.checkLevel(dto.getLevel(), dto.getDidPath());
        this.deptService.update(id, dto);
        return Result.success();
    }

    @ApiOperation("查询指定部门")
    @GetMapping("/{id}")
    public Result<DeptDto> get(@PathVariable("id") Long id) {
        return Result.success(this.deptService.get(id));
    }

    @ApiOperation("查询子部门")
    @GetMapping("/{id}/children")
    public Result<List<DeptDto>> getChildren(@PathVariable("id") Long id) {
        return Result.success(this.deptService.listChildren(id));
    }

    @ApiOperation("查询父部门")
    @GetMapping("/{id}/parent")
    public Result<DeptDto> getParent(@PathVariable("id") Long id) {
        return Result.success(this.deptService.getParent(id));
    }

    private void checkLevel(Short level, String didPath) {
        int num = didPath.split(",").length;
        if (level + 1 != num) {
            throw new ResultException(ErrCode.DEPT_LEVEL_ERROR);
        }
    }

    /**
     * 删除部门时，不能存在子部门
     * @param id
     */
    private void checkChildren(Long id) {
        // 查找子部门
        List<DeptDto> list = this.deptService.listChildren(id);
        if (!CollectionUtils.isEmpty(list)) {
            throw new ResultException(ErrCode.EXSITED_CHILDREN_DEPT);
        }
    }
}
