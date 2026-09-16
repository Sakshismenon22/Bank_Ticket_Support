package com.hexaware.bankticket.repository;

import com.hexaware.bankticket.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{

    
    
}
