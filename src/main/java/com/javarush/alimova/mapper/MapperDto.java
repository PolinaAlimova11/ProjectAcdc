package com.javarush.alimova.mapper;

import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.dto.PointDto;
import com.javarush.alimova.dto.QuestDto;
import com.javarush.alimova.entity.Action;
import com.javarush.alimova.entity.Point;
import com.javarush.alimova.entity.Quest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

@Mapper
public interface MapperDto {

    MapperDto MAPPER = Mappers.getMapper(MapperDto.class);

    ActionDto from(Action entity, List<String> listAction, Long idNextPoint);
    QuestDto from(Quest entity, Long startIdPoint);
    QuestDto from(Quest entity);
    PointDto from(Point point, Map<String, Long> listAction);

}
