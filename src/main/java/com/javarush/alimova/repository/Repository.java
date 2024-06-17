package com.javarush.alimova.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface Repository<T> {

    Stream<T> getAll();
    Optional<T> create(T value);

    Optional<T> delete(T value);

    Optional<T> update(T value);

    Optional<T> get(Long index);

    boolean isEmpty();
}
