package com.javarush.alimova.repository;

import com.javarush.alimova.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Data
@AllArgsConstructor
public class QuestRepository implements Repository<Quest>{

    private final AtomicLong indexRepo = new AtomicLong(1);
    private final Map<Long, Quest> questRepo = new HashMap<>();

    @Override
    public List<Quest> getAll() {
        return new ArrayList<>(questRepo.values());
    }

    @Override
    public Quest create(Quest value) {
        Long index = indexRepo.getAndIncrement();
        value.setId(index);
        questRepo.put(index, value);
        return value;
    }

    @Override
    public Quest delete(Quest value) {
        Long index = value.getId();
        return questRepo.remove(index);
    }

    @Override
    public Quest update(Quest value) {
        Long index = value.getId();
        Quest questOld = questRepo.get(index);
        questOld.setDescription(value.getDescription());
        questOld.setTitle(value.getTitle());
        questOld.setListPoint(value.getListPoint());
        questOld.setStartIdPoint(value.getStartIdPoint());
        return questOld;
    }

    @Override
    public Quest get(Long index) {
        return questRepo.get(index);
    }

    @Override
    public boolean isEmpty() {
        return questRepo.isEmpty();
    }
}
