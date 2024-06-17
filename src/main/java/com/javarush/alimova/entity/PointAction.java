package com.javarush.alimova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class PointAction {

    @Id
    private Long id;

    @OneToOne
    private Action action;

    @OneToOne
    @JoinColumn(name = "next_point_id")
    private Point nextPoint;
}
