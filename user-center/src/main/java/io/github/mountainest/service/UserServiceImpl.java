package io.github.mountainest.service;

import io.github.mountainest.db.IUserDbService;
import io.github.mountainest.po.UserPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDbService userDbService;

    @Override
    public void add(UserPo po) {
        this.userDbService.save(po);
    }
}
