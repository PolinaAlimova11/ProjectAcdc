package com.javarush.alimova.service;


import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.dto.QuestDto;

import java.util.Collection;

public interface QuestService {

    void initBaseQuest();

    Collection<QuestDto> getAll();

    String getTitle(Long id);

    String getDescription(Long id);

    QuestDto getQuest(Long id);

}
