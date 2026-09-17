package com.hexaware.bankticket.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.bankticket.dto.TicketCommentsDTO;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.exceptions.UserNotFoundException;
import com.hexaware.bankticket.service.TicketCommentsService;

import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/ticket-comments")
@RequiredArgsConstructor 
public class TicketCommentsController {
    
    TicketCommentsService commentsService;

    @PostMapping("/create-comment")
    public TicketCommentsDTO createTicketComment(@RequestBody TicketCommentsDTO dto) throws UserNotFoundException, TicketNotFoundException{
        return commentsService.createTicketComment(dto);
    }

    @GetMapping("/all-comments-by-id/{ticketId}")
    public List<TicketCommentsDTO> getAllCommentsById(@PathVariable int ticketId){
        return commentsService.getAllCommentsById(ticketId);
    }

    
}
