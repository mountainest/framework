package io.github.mountainest.db.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.mountainest.db.IPostDbService;
import io.github.mountainest.dto.PostQuery;
import io.github.mountainest.mapper.PostMapper;
import io.github.mountainest.po.PostPo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostDbServiceImpl extends ServiceImpl<PostMapper, PostPo> implements IPostDbService {
    @Override
    public List<PostPo> list(PostQuery query) {
        Wrapper<PostPo> wrapper = new LambdaQueryWrapper<PostPo>()
            .eq(PostPo::getFemaleFlg, query.getFemaleFlg())
            .eq(PostPo::getLocation, query.getLocation())
            .gt(PostPo::getHeight, query.getMinHeight())
            .gt(PostPo::getAnnualSalary, query.getMinAnnualSalary())
            .lt(PostPo::getUtime, query.getUtime())
            .orderByDesc(PostPo::getUtime)
            .last("limit 10");

        return this.list(wrapper);
    }
}
