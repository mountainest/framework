package io.github.mountainest.db.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.mountainest.db.IDeptDbService;
import io.github.mountainest.dto.DeptDto;
import io.github.mountainest.mapper.DeptMapper;
import io.github.mountainest.po.DeptPo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptDbServiceImpl extends ServiceImpl<DeptMapper, DeptPo> implements IDeptDbService {
    @Resource
    private DeptMapper deptMapper;

    @Override
    public List<DeptPo> listChildren(Long id) {
        return this.deptMapper.listChildren(id);
    }

    @Override
    public DeptDto getParent(Long id) {
        return this.deptMapper.getParent(id);
    }
}
