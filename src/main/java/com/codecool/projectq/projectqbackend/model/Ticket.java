package com.codecool.projectq.projectqbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ticket {

    @Id
    @GeneratedValue
    private long id;
    private long timeOfRegistration;
    private long beforeMe;
    private long estimatedTimeOfAppointment;

    @Enumerated(EnumType.STRING)
    private CaseType caseType;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.WAITING;

    @ManyToOne
    @JsonIgnore
    private Station station;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @JsonIgnore // to prevent recursive serialization attempt
    private Office office;

}
