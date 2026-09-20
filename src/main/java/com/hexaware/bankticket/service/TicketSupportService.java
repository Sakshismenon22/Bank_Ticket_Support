package com.hexaware.bankticket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.bankticket.dto.TicketDTO;
import com.hexaware.bankticket.entity.Ticket;
import com.hexaware.bankticket.enums.Status;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;

import com.hexaware.bankticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketSupportService {
    
    private final TicketRepository ticketRepository;



    public List<TicketDTO> getAllTickets(){
        
       List<Ticket> list =  ticketRepository.findAll();

        List<TicketDTO> dtoList = list.stream().map(this :: entityToDTOMapping).toList();
    
        return dtoList;
    }

    public TicketDTO updateStatus(int ticketId, Status newStatus) throws TicketNotFoundException{

        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("No matching ticket found"));

        ticket.setStatus(newStatus);

        Ticket updatedTicket = ticketRepository.save(ticket);

        return entityToDTOMapping(updatedTicket);
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
