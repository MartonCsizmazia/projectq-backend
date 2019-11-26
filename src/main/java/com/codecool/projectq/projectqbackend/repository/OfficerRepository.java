package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.Officer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficerRepository extends JpaRepository<Officer, Long> {
}
