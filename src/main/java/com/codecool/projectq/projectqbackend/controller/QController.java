package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.controller.requestdata.TicketRequestData;
import com.codecool.projectq.projectqbackend.controller.requestdata.WelcomePageData;
import com.codecool.projectq.projectqbackend.service.OfficeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codecool.projectq.projectqbackend.model.Ticket;

@CrossOrigin
@RestController
@Slf4j
public class QController {

    private OfficeService officeService;

    @Autowired
    public QController(OfficeService officeService) { //REPOSITORY ANNOTATION ADDED TO OFFICESERVICE if not -> no beans of officeService type found
        this.officeService = officeService;
    }

    @PostMapping("/requestnumber")
    public Ticket requestTicket(@RequestBody TicketRequestData ticketRequestData){
        return officeService.addTicket(ticketRequestData);
    }

    @PostMapping("/")
    public WelcomePageData requestWelcomePageData(){
        return WelcomePageData.builder()
                .caseTypeList(officeService.getCaseTypeDisplayNameList())
                .offices(officeService.getAllOfficeNames())
                .build();
    }
}