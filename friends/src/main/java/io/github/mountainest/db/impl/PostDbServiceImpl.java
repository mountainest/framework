package io.github.mountainest.db.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.mountainest.db.IPostDbService;
import io.github.mountainest.mapper.PostMapper;
import io.github.mountainest.po.PostPo;
import org.springframework.stereotype.Service;

@Service
public class PostDbServiceImpl extends ServiceImpl<PostMapper, PostPo> implements IPostDbService {
}
