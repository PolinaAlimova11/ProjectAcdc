package com.javarush.alimova.entity;


import com.javarush.alimova.dto.PointDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quest {

    Long id;
    String title;
    String description;
    Long startIdPoint;
    Map<Long, PointDto> listPoint;

}
