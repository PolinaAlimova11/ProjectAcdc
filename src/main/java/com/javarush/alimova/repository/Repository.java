package com.javarush.alimova.repository;

import com.javarush.alimova.dto.QuestDto;

import java.util.List;

public interface Repository<T> {

    List<T> getAll();
    T create(T value);

    T delete(T value);

    T update(T value);

    T get(T value);
}
