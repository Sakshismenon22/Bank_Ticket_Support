package com.hexaware.bankticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hexaware.bankticket.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    
}
