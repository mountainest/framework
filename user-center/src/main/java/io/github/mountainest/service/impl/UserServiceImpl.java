package io.github.mountainest.service.impl;

import io.github.mountainest.db.IUserDbService;
import io.github.mountainest.dto.UserDto;
import io.github.mountainest.po.UserPo;
import io.github.mountainest.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDbService userDbService;

    @Override
    public void save(UserDto dto) {
        UserPo po = new UserPo();
        BeanUtils.copyProperties(dto, po);
        this.userDbService.save(po);
    }

    @Override
    public List<UserDto> list() {
        List<UserPo> poList = this.userDbService.list();
        return poList.stream().map(po -> {
            UserDto dto = new UserDto();
            BeanUtils.copyProperties(po, dto);
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(Long id) {
        UserPo po = this.userDbService.getById(id);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    @Override
    public UserDto getOne(Long uid) {
        UserPo po = this.userDbService.getOne(uid);
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }
}
