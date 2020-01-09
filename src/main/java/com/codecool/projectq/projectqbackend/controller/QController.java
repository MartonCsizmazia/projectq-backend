package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.controller.requestdata.TicketRequestData;
import com.codecool.projectq.projectqbackend.controller.responsedata.WelcomePageData;
import com.codecool.projectq.projectqbackend.model.CurrentPosition;
import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.QAppUser;
import com.codecool.projectq.projectqbackend.service.OfficeService;
import com.codecool.projectq.projectqbackend.service.UserSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codecool.projectq.projectqbackend.model.Ticket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
public class QController {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private QAppUser qAppUser;

    @Autowired
    private UserSerivce userSerivce;

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

    @GetMapping("/test")
    public List<String> test(){
        List<String> list = new ArrayList<>();
        list.add("dolok");
        list.add("minden");
        list.add("más");
        list.add("Balázs");
        list.add("<3>");
        return list;
    }

    public HashMap<String, Integer> calculateDistance(){
        return userSerivce.getDistanccesToOffices(qAppUser.getCurrentPosition().getLatitude(), qAppUser.getCurrentPosition().getLongitude());
    }



}