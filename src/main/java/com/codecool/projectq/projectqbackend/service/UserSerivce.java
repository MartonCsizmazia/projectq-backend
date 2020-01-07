package com.codecool.projectq.projectqbackend.service;

import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.QAppUser;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import com.codecool.projectq.projectqbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class UserSerivce {

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private UserRepository userRepository;

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

    public HashMap<String, Integer> getDistanccesToOffices(double userLatitude, double userLongitude){
        List<Office> offices = officeRepository.findAll();

        HashMap <String, Integer> distanccesToOffices = new HashMap<>();
        for (Office office : offices) {
            //first point: current
            //second point: office

            //Hash_Map.put(key, value)
            double officeLatitude = office.getLocation().getLatitude();
            double officeLongitude = office.getLocation().getLongitude();

            /*
            double userLatitude = currentPosition.getLatitude();
            double userLongitude = currentPosition.getLongitude();

             */

            double distanceApproximately = Math.sqrt(Math.pow((office.getLocation().getLatitude()-userLatitude),2)
                    +Math.pow((office.getLocation().getLongitude()-userLongitude),2));

            double distance = distance(userLatitude,userLongitude,officeLatitude,officeLongitude);

            distanccesToOffices.put(office.getName(), (int) distance);
        }
        return distanccesToOffices;
    }
}
