package com.javarush.alimova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticQuest {

    String nameQuest;
    int counterGame;
    String lastStatusGames;

    public void setResultGame(String status) {
        counterGame++;
        lastStatusGames = status;
    }

}

