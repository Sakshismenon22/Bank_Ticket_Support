package com.hexaware.bankticket.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexaware.bankticket.dto.TicketCommentsDTO;
import com.hexaware.bankticket.entity.Ticket;
import com.hexaware.bankticket.entity.TicketComments;
import com.hexaware.bankticket.entity.User;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.exceptions.UserNotFoundException;
import com.hexaware.bankticket.repository.TicketCommentsRepository;
import com.hexaware.bankticket.repository.TicketRepository;
import com.hexaware.bankticket.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TicketCommentsService {
    
    private final TicketRepository ticketRepository;

    private final TicketCommentsRepository commentsRepository;

    private final UserRepository userRepository;

    public TicketCommentsDTO createTicketComment(int ticketId, TicketCommentsDTO dto) throws UserNotFoundException, TicketNotFoundException{

        userRepository.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("No user found"));

        ticketRepository.findById(ticketId).orElseThrow(() -> new TicketNotFoundException("No ticket found"));
        
        TicketComments ticketComments = dtoToEntityMapping(dto);

        TicketComments savedComments = commentsRepository.save(ticketComments);

        return entitytoDTOMapping(savedComments);
    }

    public List<TicketCommentsDTO> getAllCommentsById(int ticketId){

        List<TicketComments> list = commentsRepository.findByTicketTicketId(ticketId);

        List<TicketCommentsDTO> dtoList = new ArrayList<>();

        for(TicketComments comment : list) {
            
            dtoList.add(entitytoDTOMapping(comment));
        }

        return dtoList;
        
    }

    public TicketComments dtoToEntityMapping(TicketCommentsDTO dto)throws UserNotFoundException, TicketNotFoundException{

        TicketComments ticketComments = new TicketComments();

        ticketComments.setCreatedAt(LocalDateTime.now());
        ticketComments.setMessage(dto.getMessage());

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new UserNotFoundException("No matchinng user found"));

        Ticket ticket = ticketRepository.findById(dto.getTicketId()).orElseThrow(() -> new TicketNotFoundException("No ticket found"));

        ticketComments.setUser(user);
        ticketComments.setTicket(ticket);
        
        return ticketComments;
    }

    public TicketCommentsDTO entitytoDTOMapping(TicketComments comments){

        TicketCommentsDTO dto = new TicketCommentsDTO();

        dto.setCreatedAt(comments.getCreatedAt());
        dto.setCommentId(comments.getCommentId());
        dto.setMessage(comments.getMessage());
        dto.setTicketId(comments.getTicket().getTicketId());
        dto.setUserId(comments.getUser().getUserId());

        return dto;

    }

    
}
