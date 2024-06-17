package com.javarush.alimova.repository;

import com.javarush.alimova.dto.QuestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

class QuestRepositoryTest {

//    private final QuestRepository questRepository = new QuestRepository();
//
//    @BeforeEach
//    void init() {
//        questRepository.create(QuestDto.builder()
//                .id(1L)
//                .title("Test")
//                .startIdPoint(1L)
//                .description("Test description")
//                .listPoint(new HashMap<>())
//                .build());
//    }
//
//    @Test
//    void getAll() {
////        List<QuestDto> listQuest = questRepository.getAll();
//        List<QuestDto> listQuest = questRepository.getAll();
//
//        Assertions.assertEquals(1, listQuest.size());
//    }
//
//    @Test
//    void create() {
//        QuestDto quest = QuestDto.builder()
//                .id(2L)
//                .listPoint(new HashMap<>())
//                .description("Test2 description")
//                .startIdPoint(2L)
//                .title("Test2")
//                .build();
//
//        QuestDto questCreate = questRepository.create(quest);
//        Assertions.assertEquals(quest, questCreate);
//    }
//
//    @Test
//    void delete() {
//        QuestDto quest = QuestDto.builder()
//                .id(1L)
//                .title("Test")
//                .startIdPoint(1L)
//                .description("Test description")
//                .listPoint(new HashMap<>())
//                .build();
//        QuestDto questDelete = questRepository.delete(quest);
//
//        Assertions.assertEquals(quest, questDelete);
//    }
//
//    @Test
//    void update() {
//        QuestDto quest = QuestDto.builder()
//                .id(1L)
//                .title("TestUpdate")
//                .startIdPoint(3L)
//                .description("Test description update")
//                .listPoint(new HashMap<>())
//                .build();
//
//        QuestDto questUpdate = questRepository.update(quest);
//        Assertions.assertEquals(quest, questUpdate);
//
//    }
//
//    @Test
//    void get() {
//        QuestDto quest = questRepository.get(1L);
//        Assertions.assertEquals(1L, quest.getId());
//    }
//
//    @Test
//    void isEmpty() {
//        Assertions.assertEquals(false, questRepository.isEmpty());
//    }
}