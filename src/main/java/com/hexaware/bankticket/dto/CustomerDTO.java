package com.hexaware.bankticket.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private int customerId;
    private int userId;
    private String mobileNumber;
    private String fullName;
    private String email;

}
