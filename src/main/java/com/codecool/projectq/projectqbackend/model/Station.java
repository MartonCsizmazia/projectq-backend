package com.codecool.projectq.projectqbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Queue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Station {

    @ManyToOne
    private Office office;

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Officer officer;

    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private CaseType caseType;

    /*
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "station", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Queue<Ticket> tickets;
     */
}
