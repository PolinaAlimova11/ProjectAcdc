package com.javarush.alimova.service.impl;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.dto.PointDto;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.Point;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.PointRepository;
import com.javarush.alimova.repository.StepActionRepository;
import com.javarush.alimova.service.PointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class PointServiceImpl implements PointService {

    private final PointRepository pointRepository;
    private final SessionCreator sessionCreator;
    private final StepActionRepository stepActionRepository;
    public static final long NUMBER_STEP = 1L;

    @Override
    public Optional<PointDto> getPoint(Long id) {

        sessionCreator.beginTransactional();
        Optional<Point> pointOptional = pointRepository.get(id);
        Optional<PointDto> pointDto = Optional.empty();
        if (pointOptional.isPresent()) {
            Map<String, Long> listAction = new HashMap<>();
            for(Action action : pointOptional.get().getActionList()) {
                String description = stepActionRepository.getNumberStepByAction(action.getId(), NUMBER_STEP);
                listAction.put(description, action.getId());
            }
            pointDto = pointOptional.map(x -> MapperDto.MAPPER.from(x, listAction));
        }
        log.info(String.format("Invoke method PointService.getPoint(id = %d)", id));
        sessionCreator.endTransactional();
        return pointDto;
    }
}
