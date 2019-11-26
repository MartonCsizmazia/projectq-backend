package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
