package io.github.mountainest.service;

import io.github.mountainest.dto.DeptDto;

public interface IDeptService {
    void save(DeptDto dto);

    DeptDto get(Long id);
}
