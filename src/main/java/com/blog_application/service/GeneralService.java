package com.blog_application.service;

import java.util.Optional;

public interface GeneralService<T> {
    Iterable<T> findAll();

    void save(T t);

    Optional<T> findById(Long id);

    void delete(Long id);

    void update(T t);
}
