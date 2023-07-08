package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.dto.PostDto;
import io.github.mountainest.dto.PostQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户帖子")
@RequestMapping("/posts")
@RestController
public class PostController {
    @ApiOperation("新增用户资料")
    @PostMapping()
    public Result<Void> add(PostDto dto) {
        return Result.success();
    }

    @ApiOperation("修改用户资料")
    @PutMapping()
    public Result<Void> update(PostDto dto) {
        return Result.success();
    }

    @ApiOperation("查找用户资料")
    @GetMapping()
    public Result<List<PostDto>> list(PostQuery query) {
        return Result.success();
    }
}