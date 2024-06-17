package com.javarush.alimova.service;

import com.javarush.alimova.dto.ActionDto;

import java.util.Optional;

public interface ActionService {

    Optional<ActionDto> getByIdWithStepAction(Long id);
}
