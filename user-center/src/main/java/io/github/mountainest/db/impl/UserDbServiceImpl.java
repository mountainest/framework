package io.github.mountainest.db.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.mountainest.db.IUserDbService;
import io.github.mountainest.mapper.UserMapper;
import io.github.mountainest.po.UserPo;
import org.springframework.stereotype.Service;

@Service
public class UserDbServiceImpl extends ServiceImpl<UserMapper, UserPo> implements IUserDbService {
    @Override
    public UserPo getOne(Long uid) {
        LambdaQueryWrapper<UserPo> wrapper = new LambdaQueryWrapper<UserPo>()
            .eq(UserPo::getUid, uid);
        return this.getOne(wrapper);
    }
}
