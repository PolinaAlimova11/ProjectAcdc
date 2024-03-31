package com.javarush.alimova.repository;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.javarush.alimova.entity.Quest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class QuestRepositoryTest {

    private final QuestRepository questRepository = new QuestRepository();

    @BeforeEach
    void init() {
        questRepository.create(Quest.builder()
                .id(1L)
                .title("Test")
                .startIdPoint(1L)
                .description("Test description")
                .listPoint(new HashMap<>())
                .build());
    }

    @Test
    void getAll() {
        List<Quest> listQuest = questRepository.getAll();
        Assertions.assertEquals(1, listQuest.size());
    }

    @Test
    void create() {
        Quest quest = Quest.builder()
                .id(2L)
                .listPoint(new HashMap<>())
                .description("Test2 description")
                .startIdPoint(2L)
                .title("Test2")
                .build();

        Quest questCreate = questRepository.create(quest);
        Assertions.assertEquals(quest, questCreate);
    }

    @Test
    void delete() {
        Quest quest = Quest.builder()
                .id(1L)
                .title("Test")
                .startIdPoint(1L)
                .description("Test description")
                .listPoint(new HashMap<>())
                .build();
        Quest questDelete = questRepository.delete(quest);

        Assertions.assertEquals(quest, questDelete);
    }

    @Test
    void update() {
        Quest quest = Quest.builder()
                .id(1L)
                .title("TestUpdate")
                .startIdPoint(3L)
                .description("Test description update")
                .listPoint(new HashMap<>())
                .build();

        Quest questUpdate = questRepository.update(quest);
        Assertions.assertEquals(quest, questUpdate);

    }

    @Test
    void get() {
        Quest quest = questRepository.get(1L);
        Assertions.assertEquals(1L, quest.getId());
    }

    @Test
    void isEmpty() {
        Assertions.assertEquals(false, questRepository.isEmpty());
    }
}