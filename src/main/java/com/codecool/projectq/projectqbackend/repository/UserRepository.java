package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.QAppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<QAppUser, Long> {

    Optional<QAppUser> findByUsername(String username);

}