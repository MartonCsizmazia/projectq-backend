package com.codecool.projectq.projectqbackend.service;

import com.codecool.projectq.projectqbackend.model.Location;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.LinkedList;

@Component
public class Office {
    private String name;
    private Location location;
    private int counter = 0;
    private Queue<Ticket> queue = new LinkedList<>();

    public Office(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public Ticket addTicket(Timestamp timestampOfRegistration) {
        return null;
    }
}
