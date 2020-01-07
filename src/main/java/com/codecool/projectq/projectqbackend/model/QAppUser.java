package com.codecool.projectq.projectqbackend.model;

import com.codecool.projectq.projectqbackend.service.OfficeGetter;
import com.codecool.projectq.projectqbackend.service.OfficeService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
//@EnableAutoConfiguration
//@Configuration
@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QAppUser {

    private OfficeGetter officeGetter;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    // roles of the user (ADMIN, USER,..)
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @OneToOne(mappedBy = "qAppUser")
    private CurrentPosition currentPosition;

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;

            dist = dist * 1.609344;

            return (dist);
        }
    }

    public HashMap <String, Integer> getDistanccesToOffices(){
        List<Office> offices = officeGetter.getAllOffices();
        HashMap <String, Integer> distanccesToOffices = new HashMap<>();
        for (Office office : offices) {
            //first point: current
            //second point: office

            //Hash_Map.put(key, value)
            double officeLatitude = office.getLocation().getLatitude();
            double officeLongitude = office.getLocation().getLongitude();

            double userLatitude = currentPosition.getLatitude();
            double userLongitude = currentPosition.getLongitude();

            double distanceApproximately = Math.sqrt(Math.pow((office.getLocation().getLatitude()-currentPosition.getLatitude()),2)
                                        +Math.pow((office.getLocation().getLongitude()-currentPosition.getLongitude()),2));

            double distance = distance(userLatitude,userLongitude,officeLatitude,officeLongitude);

            distanccesToOffices.put(office.getName(), (int) distance);
        }
        return distanccesToOffices;
    }
}