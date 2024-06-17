package com.javarush.alimova.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "step_action")
public class StepAction {

    @Id
    private Long id;
    @Column(name = "serial_number")
    private int serialNumber;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Action action;


}
