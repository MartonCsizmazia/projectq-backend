package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.CaseType;
import com.codecool.projectq.projectqbackend.model.Office;
import com.codecool.projectq.projectqbackend.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Calendar;

public interface StationRepository extends JpaRepository<Station, Long> {
    long countByCaseType(CaseType caseType);

    long countByCaseTypeAndOffice_Name(CaseType caseType, String officeName);
}
