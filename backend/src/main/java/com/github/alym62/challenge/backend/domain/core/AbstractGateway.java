package com.github.alym62.challenge.backend.domain.core;

import java.util.List;

public interface AbstractGateway<T extends AbstractEntity> {
    List<T> getList();
    T getById(Long id);
    T create(T entity);
    T updateById(Long id, T entity);
    void delete(Long id);
}
