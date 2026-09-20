package com.hexaware.bankticket.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hexaware.bankticket.dto.TicketDTO;
import com.hexaware.bankticket.entity.Customer;
import com.hexaware.bankticket.entity.Ticket;
import com.hexaware.bankticket.enums.Status;
import com.hexaware.bankticket.exceptions.CustomerNotFoundException;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.helper.TicketHelper;
import com.hexaware.bankticket.repository.CustomerRepository;
import com.hexaware.bankticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketService {
    
    
    private final TicketRepository ticketRepository;

    private final CustomerRepository customerRepository;

    private final TicketHelper ticketHelper;

    public TicketDTO createTicket(TicketDTO dto) throws CustomerNotFoundException{

        
              
        Ticket ticket = dtoToEntityMapping(dto);

        ticket.setStatus(Status.OPENED);

        

        Ticket savedTicket = ticketRepository.save(ticket);
        
        return entityToDTOMapping(savedTicket);
    }

    public TicketDTO updateTicket(int ticketId, TicketDTO dto, String username) throws TicketNotFoundException, CustomerNotFoundException{

        
        Ticket ticket = ticketHelper.getCustomerTicket(ticketId, username);

        ticket.setCategory(dto.getCategory());
        ticket.setSubject(dto.getSubject());
        ticket.setDescription(dto.getDescription());
        ticket.setUpdatedAt(LocalDateTime.now());

        Ticket updatedTicket = ticketRepository.save(ticket);
        
        return entityToDTOMapping(updatedTicket);

    }

    public String deleteTicket(int ticketId) throws TicketNotFoundException{

        if(! ticketRepository.existsById(ticketId)){

            throw new TicketNotFoundException("No matching ticket found");
        }

        ticketRepository.deleteById(ticketId);

        return "Ticket deleted";
    }

    public TicketDTO getTicketById(int ticketId, String username) throws TicketNotFoundException{

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        return entityToDTOMapping(ticket);
    }

    public List<TicketDTO> getMyTickets() throws CustomerNotFoundException{
        
        Authentication authentictaion = SecurityContextHolder.getContext().getAuthentication();

        String username = authentictaion.getName();

        Customer customer = customerRepository.findByUserUsername(username).orElseThrow(() -> new CustomerNotFoundException("No customer found"));
    
        List<Ticket> list = ticketRepository.findByCustomerCustomerId(customer.getCustomerId());

        return list.stream().map(this :: entityToDTOMapping).toList();

        
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
        dto.setStatus(ticket.getStatus());

        return dto;
    }

}
