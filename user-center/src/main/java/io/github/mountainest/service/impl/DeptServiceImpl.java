package io.github.mountainest.service.impl;

import io.github.mountainest.db.IDeptDbService;
import io.github.mountainest.dto.DeptDto;
import io.github.mountainest.po.DeptPo;
import io.github.mountainest.service.IDeptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeptServiceImpl implements IDeptService {
    @Resource
    private IDeptDbService deptDbService;

    @Override
    public void save(DeptDto dto) {
        DeptPo po = new DeptPo();
        BeanUtils.copyProperties(dto, po);
        long did = UUID.randomUUID().hashCode();
        po.setDid(did < 0 ? -did: did);
        po.setDidPath("0");
        this.deptDbService.save(po);
    }

    @Override
    public void delete(Long id) {
        this.deptDbService.removeById(id);
    }

    @Override
    public void update(Long id, DeptDto dto) {
        DeptPo po = new DeptPo();
        BeanUtils.copyProperties(dto, po);
        po.setId(id);
        this.deptDbService.updateById(po);
        // TODO： 如果修改了pid，要修改didPath
    }

    @Override
    public DeptDto get(Long id) {
        DeptPo po = this.deptDbService.getById(id);
        DeptDto dto = new DeptDto();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    @Override
    public DeptDto getOne(Long did) {
        DeptPo po = this.deptDbService.getOne(did);
        DeptDto dto = new DeptDto();
        BeanUtils.copyProperties(po, dto);
        return dto;
    }

    @Override
    public List<DeptDto> listChildren(Long id) {
        List<DeptPo> poList = this.deptDbService.listChildren(id);
        List<DeptDto> dtoList = poList.stream().map(po -> {
            DeptDto dto = new DeptDto();
            BeanUtils.copyProperties(po, dto);
            return dto;
        }).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public DeptDto getParent(Long id) {
        return this.deptDbService.getParent(id);
    }
}
