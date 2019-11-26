package com.codecool.projectq.projectqbackend.service;


import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    public List<String> getAllOfficeNames(){
        List<Office> offices = officeRepository.findAll();
        return offices.stream()
                .map(Office::getName)
                .collect(Collectors.toList());
    }

}
