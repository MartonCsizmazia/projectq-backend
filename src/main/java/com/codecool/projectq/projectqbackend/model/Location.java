package com.codecool.projectq.projectqbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class Location {
    private int latitude;
    private int longitude;
}
