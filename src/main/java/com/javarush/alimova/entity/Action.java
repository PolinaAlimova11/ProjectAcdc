package com.javarush.alimova.entity;

import com.javarush.alimova.configure.StatusPoint;
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
@Table(name = "action")
public class Action {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_point")
    private StatusPoint statusPoint;

    @ManyToOne(fetch = FetchType.LAZY)
    private Point point;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "action_id")
    private List<StepAction> stepActionList;

}
