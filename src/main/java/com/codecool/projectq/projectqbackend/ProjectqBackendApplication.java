package com.codecool.projectq.projectqbackend;

import com.codecool.projectq.projectqbackend.model.*;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class ProjectqBackendApplication {

    @Autowired
    private OfficeRepository officeRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjectqBackendApplication.class, args);
    }

}
