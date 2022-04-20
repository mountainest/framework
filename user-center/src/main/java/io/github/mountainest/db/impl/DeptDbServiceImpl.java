package io.github.mountainest.db.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.mountainest.db.IDeptDbService;
import io.github.mountainest.mapper.DeptMapper;
import io.github.mountainest.po.DeptPo;
import org.springframework.stereotype.Service;

@Service
public class DeptDbServiceImpl extends ServiceImpl<DeptMapper, DeptPo> implements IDeptDbService {
}
