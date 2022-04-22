package io.github.mountainest.db;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.mountainest.po.DeptPo;

import java.util.List;

public interface IDeptDbService extends IService<DeptPo> {
    List<DeptPo> listChildren(Long id);
}
