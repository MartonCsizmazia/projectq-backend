package com.codecool.projectq.projectqbackend.service;

import com.codecool.projectq.projectqbackend.model.QAppUser;
import com.codecool.projectq.projectqbackend.model.RegistrationData;
import com.codecool.projectq.projectqbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;


@Service
public class RegistrationService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    public RegistrationService() {
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public void newUserRegistration(RegistrationData registrationData) throws RuntimeException {

        if (userRepository.findByUsername(registrationData.getEmail()).isPresent()) {
            throw new RuntimeException("You already registered with this email!");
        } else if (userRepository.findByUsername(registrationData.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exist!");
        } else {
            QAppUser newUser = QAppUser.builder()
                    .email(registrationData.getEmail())
                    .password(passwordEncoder.encode(registrationData.getPassword()))
                    .username(registrationData.getUsername())
                    .roles(Arrays.asList("ROLE_USER"))
                    .build();
            userRepository.save(newUser);
        }

    }
}
