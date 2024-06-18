package com.javarush.alimova.repository;

import com.javarush.alimova.configure.SessionCreator;
import com.javarush.alimova.entity.Quest;

public class QuestRepository extends BaseRepository<Quest>{
    public QuestRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Quest.class);
    }

}
