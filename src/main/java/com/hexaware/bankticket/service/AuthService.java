package com.hexaware.bankticket.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hexaware.bankticket.config.SecurityConfig;
import com.hexaware.bankticket.dto.AuthResponse;
import com.hexaware.bankticket.dto.LoginDTO;
import com.hexaware.bankticket.dto.RegisterDTO;
import com.hexaware.bankticket.entity.Customer;
import com.hexaware.bankticket.entity.User;
import com.hexaware.bankticket.enums.Role;
import com.hexaware.bankticket.exceptions.UserNotFoundException;
import com.hexaware.bankticket.repository.CustomerRepository;
import com.hexaware.bankticket.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class AuthService {
    
    private UserRepository userRepository;

    private CustomerRepository customerRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    public String register(RegisterDTO dto) throws UserNotFoundException{

        if(!userRepository.existsByUsername(dto.getUsername())){
            throw new UserNotFoundException("No username found");
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

        return new AuthResponse();

        
    }


}
