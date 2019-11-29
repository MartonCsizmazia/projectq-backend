package com.codecool.projectq.projectqbackend.repository;

import com.codecool.projectq.projectqbackend.model.CaseType;
import com.codecool.projectq.projectqbackend.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    long countByCaseTypeAndOffice_Name(CaseType caseType, String officeName);
}
