package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.service.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.Ticket;

import java.sql.Timestamp;
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
    public int requestNumber(@RequestBody HashMap<String,String> map){
        String time = map.get("time");
        System.out.println("got it,time:  " + time);
        long timeOfRegistration = Long.parseLong(time);
        Timestamp timestampOfRegistration = new Timestamp(timeOfRegistration);
        System.out.println("timestamp: " + timestampOfRegistration);

        Ticket ticket = office.addTicket(timestampOfRegistration);

        return 123;
    }

}
