package com.javarush.alimova.service.impl;

import com.javarush.alimova.configure.Creator;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.repository.ActionRepository;
import com.javarush.alimova.repository.QuestRepository;
import com.javarush.alimova.service.QuestService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class QuestServiceImpl implements QuestService {

    private final QuestRepository questRepository = Creator.getComponent(QuestRepository.class);
    private final ActionRepository actionRepository = Creator.getComponent(ActionRepository.class);
    private final ActionServiceImpl actionService = Creator.getComponent(ActionServiceImpl.class);

    public static final Logger log = LoggerFactory.getLogger(QuestServiceImpl.class);

    @Override
    public void initBaseQuest() {
        if (questRepository.isEmpty()) {
//            initQuestOne();
//            initQuestTwo();
        }
    }

    @Override
    public Collection<QuestDto> getAll() {

//        return questRepository.getAll();
        return null;
//        return questRepository
    }

    @Override
    public String getTitle(Long id) {
//        return questRepository.get(id).getTitle();
        return null;
    }

    @Override
    public String getDescription(Long id) {
//        return questRepository.get(id).getDescription();
        return null;
    }

    @Override
    public QuestDto getQuest(Long id) {

//        return questRepository.get(id);
        return null;
    }

//    private void initQuestOne() {
//        String title = "Похищение инопланетянами";
//        log.info("Create quest 'Alien abduction'");
//        String description = "Ты просыпаешься ночью без памяти. На тебя смотрят неизвестные существа.";
//        Map<Long, PointDto> mapPoint = new HashMap<>();
//
//
//        Map<String, Long> firstActionMap = new HashMap<>();
//
//        List<String> actionToActionDto1 = new ArrayList<>();
//        actionToActionDto1.add("Отклонить вызов");
//        actionToActionDto1.add("Ты отклонил вызов");
//
//        ActionDto actionDto1 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.LOSS)
//                .listAction(actionToActionDto1)
//                .build());
//
//        firstActionMap.put(actionDto1.getListAction().getFirst(), actionDto1.getId());
//
//        List<String> actionToActionDto2 = new ArrayList<>();
//        actionToActionDto2.add("Принять вызов");
//
//        ActionDto actionDto2 = actionRepository.create(ActionDto.builder()
//                        .listAction(actionToActionDto2)
//                        .status(StatusPoint.ACTIVE)
//                        .idNextPoint(2L)
//                .build());
//        firstActionMap.put(actionDto2.getListAction().getFirst(), actionDto2.getId());
//
//        PointDto pointOne = PointDto.builder()
//                .id(1L)
//                .Question("Ты потерял память. Принять вызов НЛО?")
//                .listAction(firstActionMap)
//                .build();
//
//        mapPoint.put(1L, pointOne);
//
//
//
//        Map<String, Long> secondActionMap = new HashMap<>();
//
//        List<String> actionToActionDto3 = new ArrayList<>();
//        actionToActionDto3.add("Отказаться подниматься на мостик");
//        actionToActionDto3.add("Ты не пошёл на переговоры. Поражение");
//
//        ActionDto actionDto3 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.LOSS)
//                .listAction(actionToActionDto3)
//                .build());
//
//        secondActionMap.put(actionDto3.getListAction().getFirst(), actionDto3.getId());
//
//        List<String> actionToActionDto4 = new ArrayList<>();
//        actionToActionDto4.add("Подняться на мостик");
//
//        ActionDto actionDto4 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.ACTIVE)
//                .listAction(actionToActionDto4)
//                .idNextPoint(3L)
//                .build());
//
//        secondActionMap.put(actionDto4.getListAction().getFirst(), actionDto4.getId());
//
//        PointDto pointTwo = PointDto.builder()
//                .id(2L)
//                .Question("Ты принял вызов. Поднимаешься на мостик к капитану?")
//                .listAction(secondActionMap)
//                .build();
//
//        mapPoint.put(2L, pointTwo);
//
//
//        Map<String, Long> thirdActionMap = new HashMap<>();
//
//        List<String> actionToActionDto5 = new ArrayList<>();
//        actionToActionDto5.add("Солгать о себе");
//        actionToActionDto5.add("Твою ложь разоблачили. Поражение");
//
//        ActionDto actionDto5 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.LOSS)
//                .listAction(actionToActionDto5)
//                .build());
//
//        thirdActionMap.put(actionDto5.getListAction().getFirst(), actionDto5.getId());
//
//        List<String> actionToActionDto6 = new ArrayList<>();
//        actionToActionDto6.add("Рассказать правду о себе.");
//        actionToActionDto6.add("Тебя вернули домой. Победа");
//
//        ActionDto actionDto6 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.WIN)
//                .listAction(actionToActionDto6)
//                .build());
//
//        thirdActionMap.put(actionDto6.getListAction().getFirst(), actionDto6.getId());
//
//        PointDto pointThree = PointDto.builder()
//                .id(3L)
//                .Question("Ты поднялся на мостик. Кто ты?")
//                .listAction(thirdActionMap)
//                .build();
//
//        mapPoint.put(3L, pointThree);
//
//        questRepository.create(QuestDto.builder()
//                        .description(description)
//                        .startIdPoint(1L)
//                        .listPoint(mapPoint)
//                        .title(title)
//                .build());
//    }
//
//    private void initQuestTwo() {
//        String title = "Сад лягушек";
//        log.info("Create quest 'Frog garden'");
//        String description = "Вроде до этого ты ложился в своей кровати и сладко засыпал." +
//                " Однако сейчас над тобой висит лицо лягушки, которая заговорила с тобой...";
//        Map<Long, PointDto> mapPoint = new HashMap<>();
//
//
//        Map<String, Long> firstActionMap = new HashMap<>();
//
//        List<String> actionToActionDto1 = new ArrayList<>();
//        actionToActionDto1.add("Посчитать, что это бред");
//        actionToActionDto1.add("Ущепнуть себя");
//        actionToActionDto1.add("Ты проснулся");
//
//        ActionDto actionDto1 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.WIN)
//                .listAction(actionToActionDto1)
//                .build());
//
//        firstActionMap.put(actionDto1.getListAction().getFirst(), actionDto1.getId());
//
//        List<String> actionToActionDto2 = new ArrayList<>();
//        actionToActionDto2.add("Поздороваться с лягушкой");
//        actionToActionDto2.add("Поприветствовать её");
//
//        ActionDto actionDto2 = actionRepository.create(ActionDto.builder()
//                .listAction(actionToActionDto2)
//                .status(StatusPoint.ACTIVE)
//                .idNextPoint(2L)
//                .build());
//        firstActionMap.put(actionDto2.getListAction().getFirst(), actionDto2.getId());
//
//        PointDto pointOne = PointDto.builder()
//                .id(1L)
//                .Question("Над тобой лягушка, которая говорит: 'Ты добрый воин, который должен пройти посвящение. " +
//                        "Впереди нас ждёт короткий путь, чтобы стать избранным'")
//                .listAction(firstActionMap)
//                .build();
//
//        mapPoint.put(1L, pointOne);
//
//
//
//        Map<String, Long> secondActionMap = new HashMap<>();
//
//        List<String> actionToActionDto3 = new ArrayList<>();
//        actionToActionDto3.add("Насторожиться, оглянуться вокруг");
//        actionToActionDto3.add("Ты замечаешь узкую дверь прямо в воздухе позади себя");
//        actionToActionDto3.add("Ты сбегаешь из сна и просыпаешься в недоумении");
//
//        ActionDto actionDto3 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.WIN)
//                .listAction(actionToActionDto3)
//                .build());
//
//        secondActionMap.put(actionDto3.getListAction().getFirst(), actionDto3.getId());
//
//        List<String> actionToActionDto4 = new ArrayList<>();
//        actionToActionDto4.add("Пойти за лягушкой по тропинке");
//
//        ActionDto actionDto4 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.ACTIVE)
//                .listAction(actionToActionDto4)
//                .idNextPoint(3L)
//                .build());
//
//        secondActionMap.put(actionDto4.getListAction().getFirst(), actionDto4.getId());
//
//        PointDto pointTwo = PointDto.builder()
//                .id(2L)
//                .Question("Лягушка тянет тебя за рукав. Впереди тебя пахучий сад и тонкая тропинка. Ты послушно идёшь за лягушкой")
//                .listAction(secondActionMap)
//                .build();
//
//        mapPoint.put(2L, pointTwo);
//
//
//        Map<String, Long> thirdActionMap = new HashMap<>();
//
//        List<String> actionToActionDto5 = new ArrayList<>();
//        actionToActionDto5.add("Жук шевелится в лапке лягушки. Почувствовать отвращение");
//        actionToActionDto5.add("Ты отказываешься. Лягушка начинает злиться на то, что ты не принял её угощение");
//        actionToActionDto5.add("Лягушка увеличивается в размерах. Теперь она глотает вас как жука");
//        actionToActionDto5.add("Вы просыпаетесь в холодном поту, чувствуя боль во всём теле. Однако теперь у вас нет ног под одеялом...");
//
//        ActionDto actionDto5 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.LOSS)
//                .listAction(actionToActionDto5)
//                .build());
//
//        thirdActionMap.put(actionDto5.getListAction().getFirst(), actionDto5.getId());
//
//        List<String> actionToActionDto6 = new ArrayList<>();
//        actionToActionDto6.add("Во сне всё нереально. Жук вряд ли будет иметь какой-то вкус");
//        actionToActionDto6.add("Согласиться на угощение. Съесть жука под довольное кваканье жабы");
//
//        ActionDto actionDto6 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.ACTIVE)
//                .listAction(actionToActionDto6)
//                .idNextPoint(4L)
//                .build());
//
//        thirdActionMap.put(actionDto6.getListAction().getFirst(), actionDto6.getId());
//
//        PointDto pointThree = PointDto.builder()
//                .id(3L)
//                .Question("Лягушка начинает рассказывать про просвящение. Один из шагов - съесть протянутого жука")
//                .listAction(thirdActionMap)
//                .build();
//
//        mapPoint.put(3L, pointThree);
//
//        Map<String, Long> fourActionMap = new HashMap<>();
//
//        List<String> actionToActionDto7 = new ArrayList<>();
//        actionToActionDto7.add("Невольно согласиться с лягушкой");
//        actionToActionDto7.add("Ты давно не бывал в просторах успокаивающей природы. Здесь тебе хорошо и не хочется уходить");
//        actionToActionDto7.add("Ты чувствуешь, как твои руки уменьшаются, а между пальцами появляются перепонки");
//        actionToActionDto7.add("Всё не важно, ведь теперь ты просто лягушка, наслаждающаяся жизнью под солнцем на кувшинке");
//
//        ActionDto actionDto7 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.WIN)
//                .listAction(actionToActionDto7)
//                .build());
//
//        fourActionMap.put(actionDto7.getListAction().getFirst(), actionDto7.getId());
//
//        List<String> actionToActionDto8 = new ArrayList<>();
//        actionToActionDto8.add("Как бы здесь не было хорошо, ты не хочешь быть лягушкой");
//        actionToActionDto8.add("Это злит лягушку. Она толкает тебя в пруд, где за твои ноги хватаются чужие лапы. Они утаскивают тебя под воду");
//        actionToActionDto8.add("Ты просыпаешься в ванной, чуть ли не захлебнувшись водой.");
//
//        ActionDto actionDto8 = actionRepository.create(ActionDto.builder()
//                .status(StatusPoint.LOSS)
//                .listAction(actionToActionDto8)
//                .build());
//
//        fourActionMap.put(actionDto8.getListAction().getFirst(), actionDto8.getId());
//
//        PointDto pointFour = PointDto.builder()
//                .id(4L)
//                .Question("Вокруг поют птицы и жужжат насекомые. После серых будней нахождение в саду с разговаривающей лягушкой" +
//                        " кажется чем-то уникальным. Лягушка ненавязчиво говорит, что их жизнь лишена волнения и страха")
//                .listAction(fourActionMap)
//                .build();
//
//        mapPoint.put(4L, pointFour);
//
//        questRepository.create(QuestDto.builder()
//                .description(description)
//                .startIdPoint(1L)
//                .listPoint(mapPoint)
//                .title(title)
//                .build());
//    }
}
