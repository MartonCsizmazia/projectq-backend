package com.codecool.projectq.projectqbackend.controller;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;


@RestController
public class QController {

    @PostMapping("/requestnumber")
    public int requestNumber(@RequestBody Timestamp time){
        System.out.println("got it");
        return 123;
    }

}
