package io.github.mountainest.service.impl;

import io.github.mountainest.db.IDeptDbService;
import io.github.mountainest.dto.DeptDto;
import io.github.mountainest.po.DeptPo;
import io.github.mountainest.service.IDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeptServiceImpl implements IDeptService {
    @Resource
    private IDeptDbService deptDbService;

    @Override
    public void save(DeptDto dto) {
        DeptPo po = new DeptPo();
        BeanUtils.copyProperties(dto, po);
        this.deptDbService.save(po);
    }
}
