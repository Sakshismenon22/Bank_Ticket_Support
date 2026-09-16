package com.hexaware.bankticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hexaware.bankticket.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    
    Optional<Customer> findByEmail(String email); 
}
