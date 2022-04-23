package io.github.mountainest.service;

import io.github.mountainest.dto.DeptDto;

import java.util.List;

public interface IDeptService {
    void save(DeptDto dto);

    void delete(Long id);

    void update(Long id, DeptDto dto);

    DeptDto get(Long id);

    DeptDto getOne(Long did);

    List<DeptDto> listChildren(Long id);

    DeptDto getParent(Long id);
}
