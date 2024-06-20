package com.javarush.alimova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestDto {

    private Long id;
    private String title;
    private String description;
    private Long startIdPoint;
}
