package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.model.CaseType;
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
        String officeName = "Győri iroda"; // TODO get from frontend (request body map)
        CaseType caseType = CaseType.MEDICAL; // TODO get from frontend (request body map)
        Ticket ticket = officeService.addTicket(officeName, caseType);
        return ticket;
    }

    @PostMapping("/")
    public List<List> requestCaseList(@RequestBody HashMap<String,String> map){

        List<CaseType> caseTypeList = officeService.getCaseTypeList();
        List<String> offices = officeService.getAllOfficeNames();
        List<List> sendList = new ArrayList<>();

        sendList.add(caseTypeList);
        sendList.add(offices);

        return sendList;
    }
}