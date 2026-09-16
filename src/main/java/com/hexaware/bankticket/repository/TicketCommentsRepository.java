package com.hexaware.bankticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.bankticket.entity.TicketComments;


@Repository
public interface TicketCommentsRepository extends JpaRepository<TicketComments, Integer>{
    
}
