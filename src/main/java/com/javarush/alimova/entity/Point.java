package com.javarush.alimova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Point {

    @Id
    private Long id;
    @Column(name = "question", columnDefinition = "TEXT")
    private String question;

    @ManyToOne
    private Quest quest;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "point_id")
    private List<Action> actionList;
}