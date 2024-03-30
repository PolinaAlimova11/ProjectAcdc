package com.javarush.alimova.dto;

import com.javarush.alimova.entity.Quest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

