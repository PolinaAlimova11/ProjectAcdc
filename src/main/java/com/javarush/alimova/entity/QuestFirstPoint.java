package com.javarush.alimova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quest_first_point")
public class QuestFirstPoint {

    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "quest_id")
    private Quest quest;

    @OneToOne
    @JoinColumn(name = "point_id")
    private Point point;
}
