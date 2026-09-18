package com.hexaware.bankticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.bankticket.entity.Customer;

 
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    
    Optional<Customer> findByEmail(String email); 

    Optional<Customer> findByUserUsername(String username);
}
