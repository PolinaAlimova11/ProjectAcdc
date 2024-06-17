package com.javarush.alimova.service.impl;

import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.StepAction;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.ActionRepository;
import com.javarush.alimova.repository.StepActionRepository;
import com.javarush.alimova.service.ActionService;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@AllArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;
    private final StepActionRepository stepActionRepository;


    @Override
    public ActionDto getById(Long id) {
        Optional<Action> actionOptional = actionRepository.getById(id);
        if (actionOptional.isPresent()) {
            Stream<StepAction> stepActionStream = stepActionRepository.getByActionOrderBySerialNumberAsc(actionOptional.get());
            return MapperDto.MAPPER.from(actionOptional.get(),
                    stepActionStream.map(StepAction::getDescription).toList(), 1L);
        }
        return null;
    }
}
