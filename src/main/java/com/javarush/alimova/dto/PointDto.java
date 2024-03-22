package com.javarush.alimova.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointDto {

    Long id;
    String Question;
    List<ActionDto> listAction;
}
