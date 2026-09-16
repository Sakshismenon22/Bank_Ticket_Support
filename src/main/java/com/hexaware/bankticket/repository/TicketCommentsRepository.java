package com.hexaware.bankticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.bankticket.entity.TicketComments;

public interface TicketCommentsRepository extends JpaRepository<TicketComments, Integer>{
    
}
