package com.codecool.projectq.projectqbackend.model;

import com.codecool.projectq.projectqbackend.model.Ticket;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class Office {

    private long id;
    private String name;
    private Location location;
    private List<Station> stations = new ArrayList<>();
    private Queue<Ticket> tickets;



    /*
    private static final long WAITTIME = 10000;

    private int counter = 0;
    private Queue<Ticket> queue = new LinkedList<>();

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

     */
}
