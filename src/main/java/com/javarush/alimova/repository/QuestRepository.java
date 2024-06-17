package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class QuestRepository extends BaseRepository<Quest>{
    public QuestRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Quest.class);
    }

    //    private final AtomicLong indexRepo = new AtomicLong(1);
//    private final Map<Long, QuestDto> questRepo = new HashMap<>();
//
//    @Override
//    public List<QuestDto> getAll() {
//        return new ArrayList<>(questRepo.values());
//    }
//
//    @Override
//    public QuestDto create(QuestDto value) {
//        Long index = indexRepo.getAndIncrement();
//        value.setId(index);
//        questRepo.put(index, value);
//        return value;
//    }
//
//    @Override
//    public QuestDto delete(QuestDto value) {
//        Long index = value.getId();
//        return questRepo.remove(index);
//    }
//
//    @Override
//    public QuestDto update(QuestDto value) {
//        Long index = value.getId();
//        QuestDto questOld = questRepo.get(index);
//        questOld.setDescription(value.getDescription());
//        questOld.setTitle(value.getTitle());
//        questOld.setListPoint(value.getListPoint());
//        questOld.setStartIdPoint(value.getStartIdPoint());
//        return questOld;
//    }
//
//    @Override
//    public QuestDto get(Long index) {
//        return questRepo.get(index);
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return questRepo.isEmpty();
//    }
}
