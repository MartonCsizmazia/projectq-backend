package com.codecool.projectq.projectqbackend.controller.requestdata;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TicketRequestData {
    private String officeName;
    private String caseType;
}
