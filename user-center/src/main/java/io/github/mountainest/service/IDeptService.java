package io.github.mountainest.service;

import io.github.mountainest.dto.DeptDto;

public interface IDeptService {
    void save(DeptDto dto);

    void delete(Long id);

    void update(Long id, DeptDto dto);

    DeptDto get(Long id);
}
