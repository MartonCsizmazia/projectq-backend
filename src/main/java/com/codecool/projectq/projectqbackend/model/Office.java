package com.codecool.projectq.projectqbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Office {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Location location;

    @Singular
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "office", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Station> stations = new ArrayList<>();

}
