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
    private int ticketId;
    private int customerId;
    private Category category;
    private Status status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
