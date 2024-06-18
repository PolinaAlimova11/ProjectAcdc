package com.javarush.alimova.service.impl;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.StepAction;
import com.javarush.alimova.exception.ConnectionDBException;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.ActionRepository;
import com.javarush.alimova.repository.PointActionRepository;
import com.javarush.alimova.repository.StepActionRepository;
import com.javarush.alimova.service.ActionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
@AllArgsConstructor
public class ActionServiceImpl implements ActionService {

    private final ActionRepository actionRepository;
    private final StepActionRepository stepActionRepository;
    private final PointActionRepository pointActionRepository;
    private final SessionCreator sessionCreator;

    @Override
    public Optional<ActionDto> getByIdWithStepAction(Long id) {

        sessionCreator.beginTransactional();
        Optional<Action> actionOptional = actionRepository.get(id);
        Optional<ActionDto> actionDto = Optional.empty();
        if (actionOptional.isPresent()) {
            Stream<StepAction> stepActionStream = stepActionRepository.getByActionOrderBySerialNumberAsc(actionOptional.get().getId());
            Long nextPoint = pointActionRepository.getIdNextPointByAction(id);
            actionDto = actionOptional.map(x -> MapperDto.MAPPER.from(x,
                    stepActionStream.map(StepAction::getDescription).toList(), nextPoint));
        }
        log.info(String.format("Invoke method ActionService.getByIdWithStepAction(id = %d)", id));
        sessionCreator.endTransactional();
        return actionDto;
    }
}
