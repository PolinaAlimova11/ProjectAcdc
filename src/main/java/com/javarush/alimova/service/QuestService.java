package com.javarush.alimova.service;


import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Quest;

import java.util.Collection;

public interface QuestService {

    void initBaseQuest();

    Collection<Quest> getAll();

    String getTitle(Long id);

    String getDescription(Long id);

    Quest getQuest(Long id);

    ActionDto getAction(Long id);

}
