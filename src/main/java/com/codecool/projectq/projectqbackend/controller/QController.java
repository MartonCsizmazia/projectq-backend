package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.controller.requestdata.TicketRequestData;
import com.codecool.projectq.projectqbackend.service.OfficeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codecool.projectq.projectqbackend.model.Ticket;

import java.util.ArrayList;
import java.util.List;

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
        Ticket ticket = null;
        try {
            ticket = officeService.addTicket(ticketRequestData);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
            // return null representing error
        }
        return ticket;
    }

    // TODO refactor to classes

    @PostMapping("/")
    public List<List> requestCaseList(){

        List<String> caseTypeList = officeService.getCaseTypeDisplayNameList();
        List<String> offices = officeService.getAllOfficeNames();
        List<List> sendList = new ArrayList<>();

        sendList.add(caseTypeList);
        sendList.add(offices);

        return sendList;
    }
}