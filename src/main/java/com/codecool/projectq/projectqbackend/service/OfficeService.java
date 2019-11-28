package com.codecool.projectq.projectqbackend.service;


import com.codecool.projectq.projectqbackend.model.CaseType;
import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.Ticket;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import com.codecool.projectq.projectqbackend.repository.StationRepository;
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

    @Autowired
    private StationRepository stationRepository;

    private static final long MINUTE = 60000; // todo 1000 for debug mode


    public List<String> getAllOfficeNames(){
        List<Office> offices = officeRepository.findAll();
        return offices.stream()
                .map(Office::getName)
                .collect(Collectors.toList());
    }

    public Ticket addTicket(String officeName, CaseType caseType) {
        long myTime = getNow();
        Ticket ticket = Ticket.builder()
                .timeOfRegistration(myTime)
                .beforeMe(getNumberOfTickets())
                .estimatedTimeOfAppointment(estimateTimeOfAppointment(myTime, caseType, officeName))
                .caseType(caseType)
                .build();
        ticketRepository.save(ticket);
        return ticket;
    }

    private long getNumberOfTickets() {
        return ticketRepository.count();
    }

    private static long getNow() {
        Instant instant = Instant.now();
        return instant.toEpochMilli();
    }

    private long estimateTimeOfAppointment(long time, CaseType caseType, String officeName){
        return time + caseType.getAvgWaitTimeInMinutes() * MINUTE * (long) Math.ceil((double) getNumberOfTickets() / (double) getNumberOfStations(caseType, officeName));
    }

    private long getNumberOfStations(CaseType caseType, String officeName) {
        return stationRepository.countByCaseTypeAndOffice_Name(caseType, officeName);
    }

    private List<CaseType> caseTypeList = new ArrayList<>(EnumSet.allOf(CaseType.class));

    public List<CaseType> getCaseTypeList() {
        return caseTypeList;
    }
}