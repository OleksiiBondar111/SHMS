package com.shms.api.mapper;

import java.util.List;


public interface EntityMapper<T, DTO> {
    DTO toDto(T entity);

    List<DTO> map(List<T> entities);

    T toEntity(DTO dto);
}
