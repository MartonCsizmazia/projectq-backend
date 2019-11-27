package com.codecool.projectq.projectqbackend.service;


import com.codecool.projectq.projectqbackend.model.CaseType;
import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.Ticket;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import com.codecool.projectq.projectqbackend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private TicketRepository ticketRepository;


    private static final long WAITTIME = 10000;

    private Queue<Ticket> queue = new LinkedList<>();


    public List<String> getAllOfficeNames(){
        List<Office> offices = officeRepository.findAll();
        return offices.stream()
                .map(Office::getName)
                .collect(Collectors.toList());
    }

    public Ticket addTicket() {
        long myTime = getNow();
        Ticket ticket = new Ticket(myTime, queue.size(), estimateTimeOfAppointment(myTime));
        queue.add(ticket);
        ticketRepository.save(ticket);
        return ticket;
    }

    private static long getNow() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    private long estimateTimeOfAppointment(long time){
        return time + WAITTIME * queue.size();
    }

    private List<CaseType> caseTypeList = new ArrayList<>(EnumSet.allOf(CaseType.class));

    public List<CaseType> getCaseTypeList() {
        return caseTypeList;
    }

}
