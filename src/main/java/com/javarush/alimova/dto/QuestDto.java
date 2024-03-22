package com.javarush.alimova.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestDto {

    Long id;
    String title;
    String description;
    Long startIdPoint;
    Map<Long, PointDto> listPoint;

}
