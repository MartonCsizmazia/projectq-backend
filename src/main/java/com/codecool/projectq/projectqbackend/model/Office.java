package com.codecool.projectq.projectqbackend.model;

import com.codecool.projectq.projectqbackend.model.Ticket;
import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Office {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Location location;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "office", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Station> stations = new ArrayList<>();


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
