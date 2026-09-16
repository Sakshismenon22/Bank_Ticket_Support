package com.hexaware.bankticket.entity;

import java.time.LocalDateTime;

import com.hexaware.bankticket.enums.Category;
import com.hexaware.bankticket.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private Category category;
    private Status status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    
}
