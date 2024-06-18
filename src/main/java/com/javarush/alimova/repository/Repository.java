package com.javarush.alimova.repository;

import java.util.Optional;
import java.util.stream.Stream;

public interface Repository<T> {

    Stream<T> getAll();
    void create(T value);

    void delete(T value);

    void update(T value);

    Optional<T> get(Long index);

    boolean isEmpty();
}
