package com.javarush.alimova.dto;

import com.javarush.alimova.configure.StatusPoint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActionDto {

    List<String> listAction;
    StatusPoint status;
    Long idPoint;

}
