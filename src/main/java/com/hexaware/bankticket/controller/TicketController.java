package com.hexaware.bankticket.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    private final TicketService ticketService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/create-ticket")
    public ResponseEntity<TicketDTO> createTicket(@RequestBody TicketDTO dto) throws CustomerNotFoundException{
       
       

       
        TicketDTO ticketDto = ticketService.createTicket(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                                .body(ticketDto);

    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @PutMapping("/update-ticket/{ticketId}")
    public ResponseEntity<TicketDTO> updateTicket(@PathVariable int ticketId, @RequestBody TicketDTO dto, Principal principal) throws TicketNotFoundException, CustomerNotFoundException{
        
        String username = principal.getName();
        
        TicketDTO ticketDto = ticketService.updateTicket(ticketId, dto, username);

        return ResponseEntity.ok(ticketDto);
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete-ticket/{ticketId}")
    public String deleteTicket(@PathVariable int ticketId) throws TicketNotFoundException{
        return ticketService.deleteTicket(ticketId);
    }

    // @PreAuthorize("hasRole('CUSTOMER')")
    // @GetMapping("/get-ticket-by-id/{ticketId}")
    // public TicketDTO getTicketById(@PathVariable int ticketId) throws TicketNotFoundException{

    //     return ticketService.getTicketById(ticketId);
    // }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/my-tickets")
    public List<TicketDTO> getMyTickets() throws CustomerNotFoundException{
        return ticketService.getMyTickets();
    }

    

}
