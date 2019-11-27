package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.codecool.projectq.projectqbackend.model.Ticket;

import java.util.HashMap;

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
        String time = map.get("time");
        System.out.println("got it,time:  " + time);
        long timeOfRegistration = Long.parseLong(time);
        Ticket ticket = officeService.addTicket(timeOfRegistration);
        return ticket;
    }
}