package io.github.mountainest.controller;

import io.github.mountainest.Result;
import io.github.mountainest.dto.PostDto;
import io.github.mountainest.dto.PostQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户帖子")
@RequestMapping("/posts")
@RestController
public class PostController {
    @ApiOperation("用户发帖")
    @PostMapping()
    public Result<Void> add(@Validated @RequestBody PostDto dto) {
        return Result.success();
    }

    @ApiOperation("修改帖子")
    @PutMapping("/{uid}")
    public Result<Void> update(@PathVariable("id") String uid, @Validated @RequestBody PostDto dto) {
        return Result.success();
    }

    @ApiOperation("查找帖子")
    @GetMapping()
    public Result<List<PostDto>> list(@Validated @RequestBody PostQuery query) {
        return Result.success();
    }
}
