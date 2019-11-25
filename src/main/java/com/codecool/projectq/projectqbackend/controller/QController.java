package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.model.Office;
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

    private final Office office;

    @Autowired
    public QController(Office office) {
        this.office = office;
    }

    @PostMapping("/requestnumber")
    public Ticket requestNumber(@RequestBody HashMap<String,String> map){
        String time = map.get("time");
        System.out.println("got it,time:  " + time);
        long timeOfRegistration = Long.parseLong(time);
        //Ticket ticket = office.addTicket(timeOfRegistration);
        //return ticket;
        return null;
    }
}
