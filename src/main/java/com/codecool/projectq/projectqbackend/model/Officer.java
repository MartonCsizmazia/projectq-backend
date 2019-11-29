package com.codecool.projectq.projectqbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Officer {

    @Id
    @GeneratedValue
    private long id;
    private String password = "1234";

    @Column(nullable = false, unique = true)
    private String userName;

    @OneToOne(mappedBy = "officer")
    @JsonIgnore
    private Station station;
}
