package com.hexaware.bankticket.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.bankticket.dto.TicketDTO;
import com.hexaware.bankticket.exceptions.CustomerNotFoundException;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.service.TicketService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/ticket")
@RequiredArgsConstructor 
public class TicketController {
    
    TicketService ticketService;

    @PostMapping("/create-ticket")
    public TicketDTO createTicket(@RequestBody TicketDTO dto) throws CustomerNotFoundException{
        return ticketService.createTicket(dto);
    }

    @PutMapping("/update-ticket")
    public TicketDTO updateTicket(@RequestBody TicketDTO dto) throws CustomerNotFoundException{
        return ticketService.updateTicket(dto);
    }

    @DeleteMapping("/delete-ticket/{ticketId}")
    public String deleteTicket(@PathVariable int ticketId) throws TicketNotFoundException{
        return ticketService.deleteTicket(ticketId);
    }

    

}
