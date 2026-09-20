package com.hexaware.bankticket.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.bankticket.dto.TicketDTO;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.service.TicketSupportService;
import com.hexaware.bankticket.enums.Status;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping ("/api/ticket-support")
@RequiredArgsConstructor 
public class TicketSupportController {
    
    private final TicketSupportService supportService;

    @PreAuthorize("hasRole('BANK_SUPPORT')")
    @GetMapping("/get-all-tickets")
    public List<TicketDTO> getAllTickets(){

        return supportService.getAllTickets();
    }

    @PreAuthorize("hasRole('BANK_SUPPORT')")
    @PutMapping("/update-status/{ticketId}/{status}")
    public TicketDTO updateStatus(@PathVariable int ticketId, @PathVariable Status status) throws TicketNotFoundException{
        return supportService.updateStatus(ticketId, status);
    }

    

}
