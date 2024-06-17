package com.javarush.alimova.mapper;

import com.javarush.alimova.dto.ActionDto;
import com.javarush.alimova.entity.Action;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapperDto {

    MapperDto MAPPER = Mappers.getMapper(MapperDto.class);

    ActionDto from(Action entity, List<String> listAction, Long idNextPoint);



}
