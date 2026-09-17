package com.hexaware.bankticket.dto;

import com.hexaware.bankticket.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class AuthResponse {
   
    private String username;

    private String token;

    private Role role;
}
