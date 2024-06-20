package com.javarush.alimova.service.impl;

import com.javarush.alimova.ConfigurationIT;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.entity.*;
import com.javarush.alimova.mapper.MapperDto;
import com.javarush.alimova.repository.*;
import com.javarush.alimova.service.QuestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestServiceIT extends ConfigurationIT {

    private final QuestRepository questRepository;
    private final ActionRepository actionRepository;
    private final QuestFirstPointRepository questFirstPointRepository;
    private final StepActionRepository stepActionRepository;
    private final PointRepository pointRepository;
    private final QuestService questService;
    private Quest testQuest;
    private Point testPoint;
    private QuestFirstPoint testQuestFirstPoint;
    private Action testAction;
    private StepAction testStepAction;

    public QuestServiceIT() {
        questRepository = Creator.getComponent(QuestRepository.class);
        actionRepository = Creator.getComponent(ActionRepository.class);
        questFirstPointRepository = Creator.getComponent(QuestFirstPointRepository.class);
        stepActionRepository = Creator.getComponent(StepActionRepository.class);
        pointRepository = Creator.getComponent(PointRepository.class);
        questService = Creator.getComponent(QuestServiceImpl.class);
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
        testQuest = Quest.builder()
                .id(1L)
                .description("descriptionTest")
                .title("title")
                .build();

        questRepository.create(testQuest);
        pointRepository.create(testPoint);
        actionRepository.create(testAction);
        stepActionRepository.create(testStepAction);
        sessionCreator.getSession().flush();

        testStepAction.setAction(testAction);
        testAction.setStepActionList(List.of(testStepAction));
        testAction.setPoint(testPoint);
        testPoint.setActionList(List.of(testAction));
        testPoint.setQuest(testQuest);
        testQuest.setPointList(List.of(testPoint));
        testQuestFirstPoint = QuestFirstPoint.builder()
                .quest(testQuest)
                .id(1L)
                .point(testPoint)
                .build();
        questFirstPointRepository.create(testQuestFirstPoint);
        sessionCreator.getSession().flush();
    }

    @AfterEach
    void tearDown() {
        questRepository.delete(testQuest);
        sessionCreator.endTransactional();
    }

    @Test
    void getAll() {
        Collection<QuestDto> questDtoList = questService.getAll();
        assertEquals(1, questDtoList.size());
    }

    @Test
    void getQuest() {
        QuestDto actualQuest = questService.getQuest(testQuest.getId()).orElseThrow();
        QuestDto expectedQuest = MapperDto.MAPPER.from(testQuest, testQuestFirstPoint.getPoint().getId());

        assertEquals(expectedQuest, actualQuest);
    }
}