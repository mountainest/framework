package io.github.mountainest.service;

import io.github.mountainest.dto.UserDto;

import java.util.List;

public interface IUserService {
    void save(UserDto po);

    List<UserDto> list();

    UserDto getById(Long id);

    UserDto getOne(Long uid);
}
