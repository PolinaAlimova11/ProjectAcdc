package com.javarush.alimova.service.impl;

import com.javarush.alimova.ConfigurationIT;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.entity.*;
import com.javarush.alimova.repository.*;
import com.javarush.alimova.service.QuestService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestServiceImplTest extends ConfigurationIT {

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

    public QuestServiceImplTest() {
        questRepository = Creator.getComponent(QuestRepository.class);
        actionRepository = Creator.getComponent(ActionRepository.class);
        questFirstPointRepository = Creator.getComponent(QuestFirstPointRepository.class);
        stepActionRepository = Creator.getComponent(StepActionRepository.class);
        pointRepository = Creator.getComponent(PointRepository.class);
        questService = Creator.getComponent(QuestServiceImpl.class);
    }

    @BeforeEach
    void setUp() {
        initTestQuest();
        sessionCreator.beginTransactional();
    }

    void initTestQuest() {
        sessionCreator.beginTransactional();
        testStepAction = StepAction.builder()
                .id(1L)
                .description("testStepAction")
                .serialNumber(1)
                .build();
        testAction = Action.builder()
                .id(1L)
                .statusPoint(StatusPoint.ACTIVE)
                .stepActionList(List.of(testStepAction))
                .build();
        testStepAction.setAction(testAction);
        testPoint = Point.builder()
                .id(1L)
                .question("testPoint")
                .actionList(List.of(testAction))
                .build();
        testAction.setPoint(testPoint);
        testQuest = Quest.builder()
                .id(1L)
                .description("descriptionTest")
                .pointList(List.of(testPoint))
                .title("title")
                .build();
        testPoint.setQuest(testQuest);
        testQuestFirstPoint = QuestFirstPoint.builder()
                .quest(testQuest)
                .id(1L)
                .point(testPoint)
                .build();

//        questRepository.create(testQuest);
//        questFirstPointRepository.create(testQuestFirstPoint);
//        pointRepository.create(testPoint);
//        actionRepository.create(testAction);
        stepActionRepository.create(testStepAction);
        //todo сначала делаем сущности, потом сбрасываем и добавляем связи

        sessionCreator.endTransactional();
    }

    @AfterEach
    void tearDown() {
        sessionCreator.endTransactional();
    }

//    @Test
//    void getAll() {
//    }
//
//    @Test
//    void getQuest() {
//    }
//
//    @Test
//    void getQuestFirstPointRepository() {
//    }
    @Test
    void test() {
        assertEquals(true, true);
    }
}