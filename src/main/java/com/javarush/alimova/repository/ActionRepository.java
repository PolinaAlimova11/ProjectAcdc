package com.javarush.alimova.repository;

import com.javarush.alimova.dto.ActionDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class ActionRepository implements Repository<ActionDto> {

    private final AtomicLong indexRepo = new AtomicLong(1);
    private final Map<Long, ActionDto> actionRepo = new HashMap<>();

    @Override
    public List<ActionDto> getAll() {
        return new ArrayList<>(actionRepo.values());
    }

    @Override
    public ActionDto create(ActionDto value) {
        Long index = indexRepo.getAndIncrement();
        value.setId(index);
        actionRepo.put(index, value);
        return value;
    }

    @Override
    public ActionDto delete(ActionDto value) {
        Long index = value.getId();
        return actionRepo.remove(index);
    }

    @Override
    public ActionDto update(ActionDto value) {
        Long index = value.getId();
        ActionDto oldAction = actionRepo.get(index);
        oldAction.setListAction(value.getListAction());
        oldAction.setStatus(value.getStatus());
        oldAction.setIdNextPoint(value.getIdNextPoint());
        return oldAction;
    }

    @Override
    public ActionDto get(Long index) {
        return actionRepo.get(index);
    }

    @Override
    public boolean isEmpty() {
        return actionRepo.isEmpty();
    }
}
