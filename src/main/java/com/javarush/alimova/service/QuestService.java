package com.javarush.alimova.service;


import com.javarush.alimova.dto.QuestDto;

import java.util.Collection;
import java.util.Optional;

public interface QuestService {

    Collection<QuestDto> getAll();

    Optional<QuestDto> getQuest(Long id);

}
