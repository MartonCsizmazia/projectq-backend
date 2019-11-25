package com.codecool.projectq.projectqbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ticket {
    private long id;
    private long timeOfRegistration;
    private int beforeMe;
    private long estimatedTimeOfAppointment;
    private CaseType caseType;
}
