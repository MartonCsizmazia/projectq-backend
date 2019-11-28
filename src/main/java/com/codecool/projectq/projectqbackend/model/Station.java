package com.codecool.projectq.projectqbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Station {

    @ManyToOne
    @JsonIgnore
    private Office office;

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Officer officer;

    @Enumerated(EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private CaseType caseType;

}
