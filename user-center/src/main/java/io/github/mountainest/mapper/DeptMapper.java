package io.github.mountainest.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.mountainest.dto.DeptDto;
import io.github.mountainest.po.DeptPo;

import java.util.List;

public interface DeptMapper extends BaseMapper<DeptPo> {
    List<DeptPo> listChildren(Long id);

    DeptDto getParent(Long id);
}
