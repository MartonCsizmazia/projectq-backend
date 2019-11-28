package com.codecool.projectq.projectqbackend.service;


import com.codecool.projectq.projectqbackend.model.CaseType;
import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.Ticket;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import com.codecool.projectq.projectqbackend.repository.StationRepository;
import com.codecool.projectq.projectqbackend.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
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

    private long getNumberOfTickets(CaseType caseType, String officeName) {
        return ticketRepository.countByCaseTypeAndOffice_Name(caseType, officeName);
    }

    private long getNumberOfStations(CaseType caseType, String officeName) {
        return stationRepository.countByCaseTypeAndOffice_Name(caseType, officeName);
    }

    private long estimateTimeOfAppointment(long time, CaseType caseType, String officeName){
        final long beforeMeAtStation = (long) Math.ceil((double) getNumberOfTickets(caseType, officeName) / (double) getNumberOfStations(caseType, officeName));
        return time + caseType.getAvgWaitTimeInMinutes() * TimeUtil.MINUTE * beforeMeAtStation;
    }

    public Ticket addTicket(String officeName, String caseTypeDisplayName) throws IllegalArgumentException {
        Optional<CaseType> caseTypeOptional = CaseType.getByDisplayName(caseTypeDisplayName);
        if (caseTypeOptional.isEmpty())
            throw new IllegalArgumentException("Invalid case type display name: " + caseTypeDisplayName);
        CaseType caseType = caseTypeOptional.get();

        final Office chosenOffice = officeRepository.findByName(officeName); // todo replace officeName with office in methods?
        if (chosenOffice == null)
            throw new IllegalArgumentException("Invalid office name: " + officeName);

        long myTime = TimeUtil.getNow();
        Ticket ticket = Ticket.builder()
                .timeOfRegistration(myTime)
                .beforeMe(getNumberOfTickets(caseType, officeName))
                .estimatedTimeOfAppointment(estimateTimeOfAppointment(myTime, caseType, officeName))
                .caseType(caseType)
                .office(chosenOffice)
                .build();
        ticketRepository.save(ticket);
        return ticket;
    }
}