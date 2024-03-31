package com.javarush.alimova.repository;

import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.ActionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ActionRepositoryTest {

    private final ActionRepository actionRepository = new ActionRepository();
    @BeforeEach
    void init() {
        List<String> listAction = new ArrayList<>();
        listAction.add("Первое действие");
        listAction.add("Второе действие");
        actionRepository.create(ActionDto.builder()
                .id(1L)
                .idNextPoint(1L)
                .status(StatusPoint.ACTIVE)
                .listAction(new ArrayList<>())
                .build());
    }

    @Test
    void getAll() {
        List<ActionDto> allAction = actionRepository.getAll();
        Assertions.assertEquals(1, allAction.size());
    }

    @Test
    void create() {
        ActionDto actionDto = ActionDto.builder()
                .listAction(new ArrayList<>())
                .status(StatusPoint.LOSS)
                .idNextPoint(2L)
                .id(2L)
                .build();
        Assertions.assertEquals(actionDto, actionRepository.create(actionDto));
    }

    @Test
    void delete() {
        List<String> listAction = new ArrayList<>();
        listAction.add("Первое действие");
        listAction.add("Второе действие");
        ActionDto actionDto = ActionDto.builder()
                .id(1L)
                .idNextPoint(1L)
                .status(StatusPoint.ACTIVE)
                .listAction(new ArrayList<>())
                .build();

        ActionDto actionDelete = actionRepository.delete(actionDto);
        Assertions.assertEquals(actionDto, actionDelete);

    }

    @Test
    void update() {
        List<String> listAction = new ArrayList<>();
        listAction.add("Предпоследнее действие");
        listAction.add("Последнее действие");
        ActionDto actionDto = ActionDto.builder()
                .id(1L)
                .idNextPoint(2L)
                .status(StatusPoint.LOSS)
                .listAction(new ArrayList<>())
                .build();
        ActionDto actionUpdate = actionRepository.update(actionDto);

        Assertions.assertEquals(actionDto, actionUpdate);
    }

    @Test
    void get() {
        ActionDto actionDto = actionRepository.get(1L);
        Assertions.assertEquals(1L, actionDto.getId());
    }

    @Test
    void isEmpty() {
        Assertions.assertEquals(false, actionRepository.isEmpty());
    }
}