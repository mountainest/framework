package io.github.mountainest.service.impl;

import io.github.mountainest.db.IUserDbService;
import io.github.mountainest.po.UserPo;
import io.github.mountainest.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDbService userDbService;

    @Override
    public void save(UserPo po) {
        this.userDbService.save(po);
    }
}
