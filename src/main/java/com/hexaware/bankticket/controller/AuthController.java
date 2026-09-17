package com.hexaware.bankticket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexaware.bankticket.dto.RegisterDTO;
import com.hexaware.bankticket.exceptions.UserNotFoundException;
import com.hexaware.bankticket.service.AuthService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor 
public class AuthController {
    
    private AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto) throws UserNotFoundException {
        
        return authService.register(dto);
    }
    
    
}
