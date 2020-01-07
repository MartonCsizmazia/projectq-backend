package com.codecool.projectq.projectqbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CurrentPosition {

    @Id
    @GeneratedValue
    private Long id;

    private double latitude;
    private double longitude;

    @OneToOne
    private QAppUser qAppUser;
}
