package com.shms.api.service;

public interface EntityService<T, DTO> {
    public T create(DTO dto);

    public void update(T entity, DTO dto);

    public T getById(String id);
}
