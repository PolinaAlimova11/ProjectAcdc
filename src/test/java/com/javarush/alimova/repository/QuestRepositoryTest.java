package com.javarush.alimova.repository;

import com.javarush.alimova.ConfigurationIT;
import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.entity.Quest;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestRepositoryTest extends ConfigurationIT {

    private final QuestRepository questRepository;
    private Quest testQuest;

    public QuestRepositoryTest() {
        questRepository = Creator.getComponent(QuestRepository.class);
    }

    @BeforeEach
    void init() {
        sessionCreator.beginTransactional();
        testQuest = Quest.builder()
                .id(1L)
                .title("TestTitle")
                .description("TestDescription")
                .pointList(Collections.emptyList())
                .build();
        questRepository.create(testQuest);
    }

    @Test
    void getAll() {
        List<Quest> questStream = questRepository.getAll().toList();
        assertEquals(1, questStream.size());
    }

    @Test
    void get() {
        Optional<Quest> actual = questRepository.get(testQuest.getId());
        actual.ifPresent(quest -> assertEquals(testQuest, quest));
    }

    @Test
    void update() {
        sessionCreator.endTransactional();
        sessionCreator.beginTransactional();

        testQuest.setTitle("updateTitle");
        questRepository.update(testQuest);
        Optional<Quest> actual = questRepository.get(testQuest.getId());
        actual.ifPresent(quest -> assertEquals(testQuest, quest));
    }

    @Test
    void delete() {
        sessionCreator.endTransactional();
        sessionCreator.beginTransactional();

        questRepository.delete(testQuest);
        assertEquals(true, questRepository.isEmpty());
    }

    @AfterEach
    void clear() {
        questRepository.delete(testQuest);
        sessionCreator.endTransactional();
    }
}