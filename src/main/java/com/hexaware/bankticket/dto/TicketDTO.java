package com.hexaware.bankticket.dto;

import java.time.LocalDateTime;

import com.hexaware.bankticket.enums.Category;
import com.hexaware.bankticket.enums.Status;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketDTO {
    private Integer ticketId;
    private Integer customerId;
    private Category category;
    private String subject;
    private Status status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
