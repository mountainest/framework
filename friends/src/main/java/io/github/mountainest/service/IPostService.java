package io.github.mountainest.service;

import io.github.mountainest.dto.PostDto;
import io.github.mountainest.dto.PostQuery;

import java.util.List;

public interface IPostService {
    void add(PostDto dto);

    void update(String uid, PostDto dto);

    List<PostDto> list(PostQuery query);
}
