package com.codecool.projectq.projectqbackend.service;


import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.Ticket;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

@Repository
public class OfficeService {

    @Autowired
    private OfficeRepository officeRepository;

    private static final long WAITTIME = 10000;

    private int counter = 0;
    private Queue<Ticket> queue = new LinkedList<>();

    public List<String> getAllOfficeNames(){
        List<Office> offices = officeRepository.findAll();
        return offices.stream()
                .map(Office::getName)
                .collect(Collectors.toList());
    }

    public Ticket addTicket(long mytime) {
        Ticket ticket = new Ticket(nextTicketId(), mytime, queue.size(),  estimateTimeOfApointment(mytime));
        queue.add(ticket);
        return ticket;
    }

    private String nextTicketId(){
        counter++;
        return String.valueOf(counter);
    }

    private long estimateTimeOfApointment(long time){
        return time+WAITTIME*queue.size();
    }


}
