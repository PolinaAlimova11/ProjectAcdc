package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Action;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ActionRepository extends BaseRepository<Action> {
    public ActionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Action.class);
    }

    public Optional<Action> getById(Long id) {
        Session session = super.getSessionCreator().getSession();
        Query<Action> query = session.createQuery("select a from Action a where id = :id", Action.class);
        query.setParameter("id", id);
        return query.uniqueResultOptional();
    }

    //    private final AtomicLong indexRepo = new AtomicLong(1);
//    private final Map<Long, ActionDto> actionRepo = new HashMap<>();
//
//    @Override
//    public List<ActionDto> getAll() {
//        return new ArrayList<>(actionRepo.values());
//    }
//
//    @Override
//    public ActionDto create(ActionDto value) {
//        Long index = indexRepo.getAndIncrement();
//        value.setId(index);
//        actionRepo.put(index, value);
//        return value;
//    }
//
//    @Override
//    public ActionDto delete(ActionDto value) {
//        Long index = value.getId();
//        return actionRepo.remove(index);
//    }
//
//    @Override
//    public ActionDto update(ActionDto value) {
//        Long index = value.getId();
//        ActionDto oldAction = actionRepo.get(index);
//        oldAction.setListAction(value.getListAction());
//        oldAction.setStatus(value.getStatus());
//        oldAction.setIdNextPoint(value.getIdNextPoint());
//        return oldAction;
//    }
//
//    @Override
//    public ActionDto get(Long index) {
//        return actionRepo.get(index);
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return actionRepo.isEmpty();
//    }
}
