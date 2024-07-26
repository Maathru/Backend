package com.maathru.backend.Domain.mapper;

public interface Mapper<T, D> {
    T toEntity(T entity, D dto);
}

