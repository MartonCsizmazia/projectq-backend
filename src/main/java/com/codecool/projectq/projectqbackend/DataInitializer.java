package com.codecool.projectq.projectqbackend;

import com.codecool.projectq.projectqbackend.model.*;
import com.codecool.projectq.projectqbackend.repository.OfficeRepository;
import com.codecool.projectq.projectqbackend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {

    private final OfficeRepository officeRepository;

    private final UserRepository users;

    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository users, OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
        this.users = users;
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void run(String... args) {
        createOffices();

        users.save(QAppUser.builder()
            .username("user")
            .password(passwordEncoder.encode("p"))
            .roles(Arrays.asList("ROLE_USER"))
            .build()
        );

        users.save(QAppUser.builder()
            .username("admin")
            .password(passwordEncoder.encode("p"))
            .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
            .build()
        );
        log.debug("printing all users...");
        users.findAll().forEach(v -> log.debug(" QAppUser :" + v.toString()));

    }

    private void createOffices() {
        Location location1 = Location.builder()
                .latitude(47.5050171)
                .longitude(19.0579319)
                .build();

        Officer petike_nagymezo = Officer.builder()
                .password("1234")
                .userName("Petike")
                .build();
        Officer orsika_nagymezo = Officer.builder()
                .password("5678")
                .userName("Orsika")
                .build();
        Officer ferike_nagymezo = Officer.builder()
                .password("9101")
                .userName("Ferike")
                .build();

        Station station1_nagymezo = Station.builder()
                .officer(petike_nagymezo)
                .caseType(CaseType.VEHICLE)
                .build();
        Station station2_nagymezo = Station.builder()
                .officer(orsika_nagymezo)
                .caseType(CaseType.MEDICAL)
                .build();
        Station station3_nagymezo = Station.builder()
                .officer(ferike_nagymezo)
                .caseType(CaseType.REAL_ESTATE)
                .build();

        Office nagymezo_utcai_iroda = Office.builder()
                .name("Nagymező utcai iroda")
                .location(location1)
                .station(station1_nagymezo)
                .station(station2_nagymezo)
                .station(station3_nagymezo)
                .build();

        station1_nagymezo.setOffice(nagymezo_utcai_iroda);
        station2_nagymezo.setOffice(nagymezo_utcai_iroda);
        station3_nagymezo.setOffice(nagymezo_utcai_iroda);

        //////////////////////////////

        Location location2 = Location.builder()
                .latitude(47.6826677)
                .longitude(17.6361037)
                .build();

        Officer sarika_gyor = Officer.builder()
                .password("2345")
                .userName("Sárika")
                .build();
        Officer jolanka_gyor = Officer.builder()
                .password("3267")
                .userName("Jolánka")
                .build();
        Officer tomika_gyor = Officer.builder()
                .password("8769")
                .userName("Tomika")
                .build();
        Officer teri_gyor = Officer.builder()
                .password("8769")
                .userName("Teri")
                .build();

        Station station1_gyor = Station.builder()
                .officer(sarika_gyor)
                .caseType(CaseType.VEHICLE)
                .build();
        Station station2_gyor = Station.builder()
                .officer(jolanka_gyor)
                .caseType(CaseType.MEDICAL)
                .build();
        Station station4_gyor = Station.builder()
                .officer(tomika_gyor)
                .caseType(CaseType.REAL_ESTATE)
                .build();

        Office gyori_iroda = Office.builder()
                .name("Győri iroda")
                .location(location2)
                .station(station1_gyor)
                .station(station2_gyor)
                .station(station4_gyor)
                .build();

        station1_gyor.setOffice(gyori_iroda);
        station2_gyor.setOffice(gyori_iroda);

        station4_gyor.setOffice(gyori_iroda);

        officeRepository.save(nagymezo_utcai_iroda);
        officeRepository.save(gyori_iroda);
    }
}
