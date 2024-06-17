package com.javarush.alimova.service;

import com.javarush.alimova.dto.PointDto;

import java.util.Optional;

public interface PointService {

    Optional<PointDto> getPoint(Long id);
}
