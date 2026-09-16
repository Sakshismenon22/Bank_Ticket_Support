package com.hexaware.bankticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.bankticket.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
}
