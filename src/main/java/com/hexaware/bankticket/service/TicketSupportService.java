package com.hexaware.bankticket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.bankticket.entity.Ticket;
import com.hexaware.bankticket.enums.Status;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.repository.CustomerRepository;
import com.hexaware.bankticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketSupportService {
    
    TicketRepository ticketRepository;

    CustomerRepository customerRepository;

    public List<Ticket> getAllTickets(){
        
        return ticketRepository.findAll();
    }

    public Ticket updateStatus(int ticketId, Status newStatus) throws TicketNotFoundException{

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("No matching ticket found"));

        if(ticket.getStatus() == (Status.OPENED)){

            if(newStatus == (Status.IN_PROGRESS)){
                ticket.setStatus(newStatus);
            }
            if(newStatus == (Status.CLOSED)){
                ticket.setStatus(newStatus);
            }
        }

        return ticket;
    }


}
