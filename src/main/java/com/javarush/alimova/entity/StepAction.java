package com.javarush.alimova.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
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

    @ManyToOne(fetch = FetchType.LAZY)
    private Action action;
}
