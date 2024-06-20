package com.javarush.alimova.service.impl;

import com.javarush.alimova.ConfigurationIT;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.Point;
import com.javarush.alimova.entity.PointAction;
import com.javarush.alimova.entity.StepAction;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.ActionRepository;
import com.javarush.alimova.repository.PointActionRepository;
import com.javarush.alimova.repository.PointRepository;
import com.javarush.alimova.repository.StepActionRepository;
import com.javarush.alimova.service.ActionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ActionServiceIT extends ConfigurationIT {

    private final ActionRepository actionRepository;
    private final StepActionRepository stepActionRepository;
    private final PointRepository pointRepository;
    private final PointActionRepository pointActionRepository;
    private final ActionService actionService;
    private PointAction testPointAction;
    private Point testPoint;
    private Action testAction;
    private StepAction testStepAction;

    private ActionServiceIT() {
        actionRepository = Creator.getComponent(ActionRepository.class);
        stepActionRepository = Creator.getComponent(StepActionRepository.class);
        pointRepository = Creator.getComponent(PointRepository.class);
        pointActionRepository = Creator.getComponent(PointActionRepository.class);
        actionService = Creator.getComponent(ActionServiceImpl.class);

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
                .id(2L)
                .question("testPoint")
                .build();

        pointRepository.create(testPoint);
        actionRepository.create(testAction);
        stepActionRepository.create(testStepAction);
        sessionCreator.getSession().flush();

        testPointAction = PointAction.builder()
                .id(1L)
                .action(testAction)
                .nextPoint(testPoint)
                .build();

        pointActionRepository.create(testPointAction);
        testStepAction.setAction(testAction);
        testAction.setStepActionList(List.of(testStepAction));
        testAction.setPoint(testPoint);
        sessionCreator.getSession().flush();
    }

    @AfterEach
    void tearDown() {
        pointRepository.delete(testPoint);
        sessionCreator.endTransactional();
    }

    @Test
    void getByIdWithStepAction() {
        ActionDto actualAction = actionService.getByIdWithStepAction(testAction.getId()).orElseThrow();

        List<String> stepActionList = List.of(testStepAction.getDescription());
        ActionDto exceptedAction = actionRepository.get(testAction.getId())
                .map(x -> MapperDto.MAPPER.from(x, stepActionList, testPoint.getId())).orElseThrow();

        assertEquals(exceptedAction, actualAction);
    }
}