package com.codecool.projectq.projectqbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@CrossOrigin
@RestController
public class QController {

    @PostMapping("/requestnumber")
    public int requestNumber(@RequestBody HashMap<String,String> map){
        System.out.println("got it,time:  " + map.get("time") );
        return 123;
    }

}
