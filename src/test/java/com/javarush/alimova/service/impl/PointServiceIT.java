package com.javarush.alimova.service.impl;

import com.javarush.alimova.ConfigurationIT;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.PointDto;
import com.javarush.alimova.entity.*;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.*;
import com.javarush.alimova.service.PointService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PointServiceIT extends ConfigurationIT {

    private final ActionRepository actionRepository;
    private final StepActionRepository stepActionRepository;
    private final PointRepository pointRepository;
    private final PointService pointService;
    private Point testPoint;
    private Action testAction;
    private StepAction testStepAction;

    public PointServiceIT() {
        actionRepository = Creator.getComponent(ActionRepository.class);
        stepActionRepository = Creator.getComponent(StepActionRepository.class);
        pointRepository = Creator.getComponent(PointRepository.class);
        pointService = Creator.getComponent(PointServiceImpl.class);
    }

    @BeforeEach
    void setUp() {
        sessionCreator.beginTransactional();
        initTestQuest();
    }

    void initTestQuest() {
        testStepAction = StepAction.builder()
                .id(1L)
                .description("testStepAction")
                .serialNumber(1)
                .build();
        testAction = Action.builder()
                .id(1L)
                .statusPoint(StatusPoint.ACTIVE)
                .build();
        testPoint = Point.builder()
                .id(1L)
                .question("testPoint")
                .build();

        pointRepository.create(testPoint);
        actionRepository.create(testAction);
        stepActionRepository.create(testStepAction);
        sessionCreator.getSession().flush();

        testStepAction.setAction(testAction);
        testAction.setStepActionList(List.of(testStepAction));
        testAction.setPoint(testPoint);
        testPoint.setActionList(List.of(testAction));
        sessionCreator.getSession().flush();
    }

    @AfterEach
    void tearDown() {
        pointRepository.delete(testPoint);
        sessionCreator.endTransactional();
    }

    @Test
    void getPoint() {
        PointDto actualPoint = pointService.getPoint(testPoint.getId()).orElseThrow();

        Map<String, Long> listAction = new HashMap<>();
        listAction.put(testStepAction.getDescription(), testAction.getId());
        PointDto exceptedPoint = MapperDto.MAPPER.from(testPoint, listAction);

        assertEquals(exceptedPoint, actualPoint);
    }
}