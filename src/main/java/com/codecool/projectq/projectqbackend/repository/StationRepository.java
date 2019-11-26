package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationRepository extends JpaRepository<Station, Long> {
}
