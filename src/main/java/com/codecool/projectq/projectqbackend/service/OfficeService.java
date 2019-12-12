package com.codecool.projectq.projectqbackend.service;


import com.codecool.projectq.projectqbackend.controller.exception.InvalidCaseTypeException;
import com.codecool.projectq.projectqbackend.controller.exception.InvalidOfficeNameException;
import com.codecool.projectq.projectqbackend.controller.exception.InvalidRequestDataException;
import com.codecool.projectq.projectqbackend.controller.requestdata.TicketRequestData;
import com.codecool.projectq.projectqbackend.model.CaseType;
import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.Ticket;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import com.codecool.projectq.projectqbackend.repository.StationRepository;
import com.codecool.projectq.projectqbackend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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


    public List<String> getCaseTypeDisplayNameList() {
        return CaseType.getAllDisplayNames();
    }

    public List<String> getAllOfficeNames(){
        List<Office> offices = officeRepository.findAll();
        return offices.stream()
                .map(Office::getName)
                .collect(Collectors.toList());
    }

    private long getNumberOfTickets(CaseType caseType, Office office) {
        return ticketRepository.countByCaseTypeAndOffice(caseType, office);
    }

    private long getNumberOfStations(CaseType caseType, Office office) {
        return stationRepository.countByCaseTypeAndOffice(caseType, office);
    }

    private long estimateTimeOfAppointment(long time, CaseType caseType, Office office){
        final long beforeMeAtStation = (long) Math.ceil((double) getNumberOfTickets(caseType, office) / (double) getNumberOfStations(caseType, office));
        return time + caseType.getAvgWaitTimeInMinutes() * TimeUtil.MINUTE * beforeMeAtStation;
    }

    public Ticket addTicket(TicketRequestData ticketRequestData) throws InvalidRequestDataException {
        String officeName = ticketRequestData.getOfficeName();
        String caseTypeDisplayName = ticketRequestData.getCaseType();
        // defaults: just for test/debug
        if (officeName == null)
            officeName = "Győri iroda";
        if (caseTypeDisplayName == null)
            caseTypeDisplayName = "Medical";

        Optional<CaseType> caseTypeOptional = CaseType.getByDisplayName(caseTypeDisplayName);
        if (caseTypeOptional.isEmpty())
            throw new InvalidCaseTypeException(caseTypeDisplayName);
        CaseType caseType = caseTypeOptional.get();

        final Office chosenOffice = officeRepository.findByName(officeName);
        if (chosenOffice == null)
            throw new InvalidOfficeNameException(officeName);

        long myTime = TimeUtil.getNow();
        Ticket ticket = Ticket.builder()
                .timeOfRegistration(myTime)
                .beforeMe(getNumberOfTickets(caseType, chosenOffice))
                .estimatedTimeOfAppointment(estimateTimeOfAppointment(myTime, caseType, chosenOffice))
                .caseType(caseType)
                .office(chosenOffice)
                .build();
        ticketRepository.save(ticket);
        return ticket;
    }
}