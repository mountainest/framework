package io.github.mountainest.service.impl;

import io.github.mountainest.db.IPostDbService;
import io.github.mountainest.dto.PostDto;
import io.github.mountainest.dto.PostQuery;
import io.github.mountainest.po.PostPo;
import io.github.mountainest.service.IPostService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Resource
    private IPostDbService postDbService;

    @Override
    public void add(PostDto dto) {
        PostPo po = new PostPo();
        BeanUtils.copyProperties(dto, po);

        this.postDbService.save(po);
    }

    @Override
    public void update(String uid, PostDto dto) {
        PostPo po = new PostPo();
        BeanUtils.copyProperties(dto, po);
        po.setUid(uid);

        this.postDbService.updateById(po);
    }

    @Override
    public List<PostDto> list(PostQuery query) {
        List<PostPo> list = this.postDbService.list(query);

        List<PostDto> result = new ArrayList<>();
        list.stream().forEach(po -> {
            PostDto dto = new PostDto();
            BeanUtils.copyProperties(po, dto);
            result.add(dto);
        });

        return result;
    }
}
