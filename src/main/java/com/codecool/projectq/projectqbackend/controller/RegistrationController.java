package com.codecool.projectq.projectqbackend.controller;

import com.codecool.projectq.projectqbackend.model.RegistrationData;
import com.codecool.projectq.projectqbackend.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @PostMapping("/registration")
    public void registrationPage(@RequestBody RegistrationData registerForm){
        registrationService.newUserRegistration(registerForm);
    }
}
