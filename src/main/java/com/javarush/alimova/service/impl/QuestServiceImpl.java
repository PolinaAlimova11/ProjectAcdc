package com.javarush.alimova.service.impl;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.configure.StatusPoint;
import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.dto.PointDto;
import com.javarush.alimova.entity.Quest;
import com.javarush.alimova.repository.QuestRepository;
import com.javarush.alimova.service.QuestService;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository = Creator.getComponent(QuestRepository.class);

    @Override
    public void initBaseQuest() {
        if (questRepository.isEmpty()) {
            initQuestOne();
        }
    }

    @Override
    public Collection<Quest> getAll() {
        return questRepository.getAll();
    }

    @Override
    public String getTitle(Long id) {
        return questRepository.get(id).getTitle();
    }

    @Override
    public String getDescription(Long id) {
        return questRepository.get(id).getDescription();
    }

    @Override
    public Quest getQuest(Long id) {
        return questRepository.get(id);
    }

    private void initQuestOne() {
        String title = "Похищение инопланетянами";
        String description = "Ты просыпаешься ночью без памяти. На тебя смотрят неизвестные существа.";
        Map<Long, PointDto> mapPoint = new HashMap<>();


        List<ActionDto> firstAction = new ArrayList<>();

        List<String> actionToActionDto1 = new ArrayList<>();
        actionToActionDto1.add("Отклонить вызов");
        actionToActionDto1.add("Ты отклонил вызов");

        firstAction.add(ActionDto.builder()
                .status(StatusPoint.LOSS)
                .listAction(actionToActionDto1)
                .build());

        List<String> actionToActionDto2 = new ArrayList<>();
        actionToActionDto2.add("Принять вызов");

        firstAction.add(ActionDto.builder()
                        .listAction(actionToActionDto2)
                        .status(StatusPoint.ACTIVE)
                        .idNextPoint(2L)
                .build());

        PointDto pointOne = PointDto.builder()
                .id(1L)
                .Question("Ты потерял память. Принять вызов НЛО?")
                .listAction(firstAction)
                .build();

        mapPoint.put(1L, pointOne);

        List<ActionDto> secondAction = new ArrayList<>();

        List<String> actionToActionDto3 = new ArrayList<>();
        actionToActionDto3.add("Отказаться подниматься на мостик");
        actionToActionDto3.add("Ты не пошёл на переговоры. Поражение");

        secondAction.add(ActionDto.builder()
                .status(StatusPoint.LOSS)
                .listAction(actionToActionDto3)
                .build());

        List<String> actionToActionDto4 = new ArrayList<>();
        actionToActionDto4.add("Подняться на мостик");

        secondAction.add(ActionDto.builder()
                .status(StatusPoint.ACTIVE)
                .listAction(actionToActionDto4)
                .idNextPoint(3L)
                .build());

        PointDto pointTwo = PointDto.builder()
                .id(2L)
                .Question("Ты принял вызов. Поднимаешься на мостик к капитану?")
                .listAction(secondAction)
                .build();

        mapPoint.put(2L, pointTwo);


        List<ActionDto> thirdAction = new ArrayList<>();

        List<String> actionToActionDto5 = new ArrayList<>();
        actionToActionDto5.add("Солгать о себе");
        actionToActionDto5.add("Твою ложь разоблачили. Поражение");

        thirdAction.add(ActionDto.builder()
                .status(StatusPoint.LOSS)
                .listAction(actionToActionDto5)
                .build());

        List<String> actionToActionDto6 = new ArrayList<>();
        actionToActionDto6.add("Рассказать правду о себе.");
        actionToActionDto6.add("Тебя вернули домой. Победа");

        thirdAction.add(ActionDto.builder()
                .status(StatusPoint.WIN)
                .listAction(actionToActionDto6)
                .build());

        PointDto pointThree = PointDto.builder()
                .id(3L)
                .Question("Ты поднялся на мостик. Кто ты?")
                .listAction(thirdAction)
                .build();

        mapPoint.put(3L, pointThree);

        questRepository.create(Quest.builder()
                        .description(description)
                        .startIdPoint(1L)
                        .listPoint(mapPoint)
                        .title(title)
                .build());

        //ещё один пустой квест
        questRepository.create(Quest.builder()
                        .description("пустой квест")
                        .title("пустой квест")
                        .startIdPoint(0L)
                .build());
    }
}
