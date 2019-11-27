package com.codecool.projectq.projectqbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


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
    //private long
    private CaseType caseType;

    @ManyToOne
    private Station station;

    public Ticket(long timeOfRegistration, long beforeMe, long estimatedTimeOfAppointment) {
        this.timeOfRegistration = timeOfRegistration;
        this.beforeMe = beforeMe;
        this.estimatedTimeOfAppointment = estimatedTimeOfAppointment;
    }

}
