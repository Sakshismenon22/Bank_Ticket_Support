package com.hexaware.bankticket.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.bankticket.dto.AuthResponse;
import com.hexaware.bankticket.dto.LoginDTO;
import com.hexaware.bankticket.dto.RegisterDTO;
import com.hexaware.bankticket.entity.Customer;
import com.hexaware.bankticket.entity.User;
import com.hexaware.bankticket.enums.Role;
import com.hexaware.bankticket.exceptions.UserNotFoundException;
import com.hexaware.bankticket.repository.CustomerRepository;
import com.hexaware.bankticket.repository.UserRepository;
import com.hexaware.bankticket.security.JwtService;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    
    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public String register(RegisterDTO dto) throws UserNotFoundException{

        if(userRepository.existsByUsername(dto.getUsername())){
            throw new RuntimeException("Username already exixts.");
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User();

        user.setPassword(hashedPassword);
        user.setUsername(dto.getUsername());
        user.setRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);

        Customer customer = new Customer();

        customer.setEmail(dto.getEmail());
        customer.setFullName(dto.getFullName());
        customer.setMobileNumber(dto.getMobileNumber());
        customer.setUser(savedUser);

        customerRepository.save(customer);

        return "Registered successfully";

    }

    public AuthResponse login(LoginDTO dto) throws UserNotFoundException{

        //Returns the authentication
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(()-> new UserNotFoundException("User not found"));

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

        AuthResponse response = new AuthResponse();

        response.setRole(user.getRole().name());
        response.setUsername(user.getUsername());
        response.setToken(token);

        return response;
        
    }


}
