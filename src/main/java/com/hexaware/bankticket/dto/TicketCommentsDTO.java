package com.hexaware.bankticket.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TicketCommentsDTO {
    private Integer commentId;
    private Integer userId;
    private Integer ticketId;
    private String message;
    private LocalDateTime createdAt;
}
