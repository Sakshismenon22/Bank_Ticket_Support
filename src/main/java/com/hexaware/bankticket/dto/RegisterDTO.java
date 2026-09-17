package com.hexaware.bankticket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder 
@AllArgsConstructor 
@NoArgsConstructor 
public class RegisterDTO {
    
    private String fullName;

    private String username;

    private String password;

    private String mobileNumber;
    
    private String email;

}
