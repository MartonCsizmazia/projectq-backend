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

    private static final long WAITTIME = 10000;


    public List<String> getAllOfficeNames(){
        List<Office> offices = officeRepository.findAll();
        return offices.stream()
                .map(Office::getName)
                .collect(Collectors.toList());
    }
// myTime, getNumberOfTickets(), estimateTimeOfAppointment(myTime)
    public Ticket addTicket() {
        long myTime = getNow();
        CaseType caseType = CaseType.MEDICAL; //TEST! TODO get from frontend
        String officeName = "Győri iroda"; //TEST! TODO get from frontend
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
        return time + WAITTIME * (long) Math.ceil((double) getNumberOfTickets() / (double) getNumberOfStations(caseType, officeName));
    }

    private long getNumberOfStations(CaseType caseType, String officeName) {
        return stationRepository.countByCaseTypeAndOffice_Name(caseType, officeName);
    }

    private List<CaseType> caseTypeList = new ArrayList<>(EnumSet.allOf(CaseType.class));

    public List<CaseType> getCaseTypeList() {
        return caseTypeList;
    }
}