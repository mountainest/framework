package io.github.mountainest.db;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.mountainest.dto.PostQuery;
import io.github.mountainest.po.PostPo;

import java.util.List;

public interface IPostDbService extends IService<PostPo> {
    List<PostPo> list(PostQuery query);
}
