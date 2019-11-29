package com.codecool.projectq.projectqbackend.repository;


import com.codecool.projectq.projectqbackend.model.Office;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office, Long> {
    Office findByName(String name);
}
