package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class AllRepositoryTest {
    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private OfficerRepository officerRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Location location2 = Location.builder()
            .latitude(47.6826677)
            .longitude(17.6361037)
            .build();

    private Officer sarika_gyor = Officer.builder()
            .password("2345")
            .userName("Sárika")
            .build();

    private Station station1_gyor = Station.builder()
            .officer(sarika_gyor)
            .caseType(CaseType.VEHICLE)
            .build();

    private Office gyori_iroda = Office.builder()
            .name("Győri iroda")
            .location(location2)
            .station(station1_gyor)
            .build();

    private Office gyori_iroda2 = Office.builder()
            .name("Győri iroda")
            .location(location2)
            .station(station1_gyor)
            .build();

    @Test
    public void saveStationSimple(){
        locationRepository.save(location2);

        List<Location> locationList = locationRepository.findAll();
        assertThat(locationList).hasSize(1);
    }

    @Test
    public void saveOfficerSimple(){
        officerRepository.save(sarika_gyor);

        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList).hasSize(1);
    }

    @Test
    public void saveLocationSimple(){
        stationRepository.save(station1_gyor);

        List<Station> stationList = stationRepository.findAll();
        assertThat(stationList).hasSize(1);
    }

    @Test
    public void saveOfficeSimple(){
        officeRepository.save(gyori_iroda);

        List<Office> officeList = officeRepository.findAll();
        assertThat(officeList).hasSize(1);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void saveUniqueFieldTwice(){
        officeRepository.save(gyori_iroda);

        officeRepository.saveAndFlush(gyori_iroda2);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void officeNameShouldBeNotNull(){
        Office gyori_iroda3 = Office.builder()
                .location(location2)
                .station(station1_gyor)
                .build();

        officeRepository.saveAndFlush(gyori_iroda3);
    }

    @Test
    public void OfficerIsPersistedWithStation(){

        stationRepository.save(station1_gyor);

        List<Officer> officerList = officerRepository.findAll();
        assertThat(officerList)
                .hasSize(1)
                .allMatch(officer1 -> officer1.getId() > 0L);
    }
}