package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.entity.Action;

public class ActionRepository extends BaseRepository<Action> {
    public ActionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Action.class);
    }

}
