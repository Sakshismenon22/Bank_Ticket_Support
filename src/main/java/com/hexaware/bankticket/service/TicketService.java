package com.hexaware.bankticket.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.hexaware.bankticket.dto.TicketDTO;
import com.hexaware.bankticket.entity.Customer;
import com.hexaware.bankticket.entity.Ticket;
import com.hexaware.bankticket.enums.Status;
import com.hexaware.bankticket.exceptions.CustomerNotFoundException;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.repository.CustomerRepository;
import com.hexaware.bankticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
    
    
    private TicketRepository ticketRepository;

    private CustomerRepository customerRepository;

    public TicketDTO createTicket(TicketDTO dto) throws CustomerNotFoundException{

        Ticket ticket = dtoToEntityMapping(dto);

        ticket.setStatus(Status.OPENED);

        ticket = ticketRepository.save(ticket);
        
        return entityToDTOMapping(ticket);
    }

    public TicketDTO updateTicket(TicketDTO dto) throws CustomerNotFoundException{

        Ticket ticket = dtoToEntityMapping(dto);

        ticket.setStatus(Status.OPENED);

        ticket = ticketRepository.save(ticket);
        
        return entityToDTOMapping(ticket);

    }

    public String deleteTicket(int ticketId) throws TicketNotFoundException{

        if(! ticketRepository.existsById(ticketId)){

            throw new TicketNotFoundException("No matching ticket found");
        }

        ticketRepository.deleteById(ticketId);

        return "Ticket deleted";
    }

    public TicketDTO getTicketById(int ticketId) throws TicketNotFoundException{

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        return entityToDTOMapping(ticket);
    }

    public Ticket dtoToEntityMapping(TicketDTO dto) throws CustomerNotFoundException{

        Ticket ticket = new Ticket();

        ticket.setCategory(dto.getCategory());
        ticket.setCreatedAt(LocalDateTime.now());
        ticket.setDescription(dto.getDescription());
        ticket.setSubject(dto.getSubject());
        
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new CustomerNotFoundException("No matching customer found!"));
        
        ticket.setCustomer(customer);
        ticket.setUpdatedAt(LocalDateTime.now());
        
        return ticket;

    }

    public TicketDTO entityToDTOMapping(Ticket ticket){

        TicketDTO dto = new TicketDTO();

        dto.setCategory(ticket.getCategory());
        dto.setCreatedAt(ticket.getCreatedAt());
        dto.setCustomerId(ticket.getCustomer().getCustomerId());
        dto.setDescription(ticket.getDescription());
        dto.setSubject(ticket.getSubject());
        dto.setTicketId(ticket.getTicketId());
        dto.setUpdatedAt(ticket.getUpdatedAt());

        return dto;
    }

}
