package io.github.mountainest.db;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.mountainest.po.UserPo;

public interface IUserDbService extends IService<UserPo> {
    UserPo getOne(Long uid);
}
