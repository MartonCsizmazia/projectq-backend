package com.codecool.projectq.projectqbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Location {
    @Id
    @GeneratedValue
    private long id;

    private double latitude;
    private double longitude;

    @OneToOne(mappedBy = "location")
    @JsonIgnore
    private Office office;
}