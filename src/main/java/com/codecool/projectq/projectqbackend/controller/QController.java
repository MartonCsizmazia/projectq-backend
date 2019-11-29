package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codecool.projectq.projectqbackend.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
public class QController {

    private OfficeService officeService;

    @Autowired
    public QController(OfficeService officeService) { //REPOSITORY ANNOTATION ADDED TO OFFICESERVICE if not -> no beans of officeService type found
        this.officeService = officeService;
    }

    @PostMapping("/requestnumber")
    public Ticket requestNumber(@RequestBody HashMap<String,String> map){
        // defaults are just for test/debug
        String officeName = map.getOrDefault("officeName", "Győri iroda");
        String caseTypeDisplayName = map.getOrDefault("caseType", "Medical");

        Ticket ticket = null;
        try {
            ticket = officeService.addTicket(officeName, caseTypeDisplayName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            // return null representing error
        }
        return ticket;
    }

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