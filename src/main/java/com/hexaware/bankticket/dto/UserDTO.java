package com.hexaware.bankticket.dto;

import com.hexaware.bankticket.enums.Role;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserDTO{
    
    private int userId;
    @NotBlank
    private String username;
    private String password;
    private Role role;

}