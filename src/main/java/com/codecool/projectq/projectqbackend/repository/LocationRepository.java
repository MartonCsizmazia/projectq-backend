package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
