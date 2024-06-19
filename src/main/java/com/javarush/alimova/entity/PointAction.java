package com.javarush.alimova.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "point_action")
public class PointAction {

    @Id
    private Long id;

    @OneToOne
    private Action action;

    @OneToOne
    @JoinColumn(name = "next_point_id")
    private Point nextPoint;
}
