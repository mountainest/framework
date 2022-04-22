package io.github.mountainest.service;

import io.github.mountainest.dto.UserDto;
import io.github.mountainest.po.UserPo;

import java.util.List;

public interface IUserService {
    void save(UserPo po);

    List<UserDto> list();
}
