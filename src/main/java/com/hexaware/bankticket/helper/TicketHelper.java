package com.hexaware.bankticket.helper;

import org.springframework.stereotype.Component;

import com.hexaware.bankticket.exceptions.CustomerNotFoundException;
import com.hexaware.bankticket.exceptions.TicketNotFoundException;
import com.hexaware.bankticket.entity.Customer;
import com.hexaware.bankticket.entity.Ticket;
import com.hexaware.bankticket.repository.CustomerRepository;
import com.hexaware.bankticket.repository.TicketRepository;

import lombok.RequiredArgsConstructor;

@Component 
@RequiredArgsConstructor 
public class TicketHelper {
    
    private final CustomerRepository customerRepository;
    private final TicketRepository ticketRepository;

    public Customer getCustomer(String username) throws CustomerNotFoundException{
        return customerRepository.findByUserUsername(username)
                                .orElseThrow(() -> new CustomerNotFoundException("Customer not found : " + username));
    }


    public Ticket getTicket(Integer ticketId) throws TicketNotFoundException{
        return ticketRepository.findById(ticketId)
                                .orElseThrow(() -> new TicketNotFoundException("Ticket Not Found: " + ticketId));
    }
    

    public Ticket getCustomerTicket(Integer ticketId, String username) throws CustomerNotFoundException, TicketNotFoundException{
        Customer customer = getCustomer(username);
        Ticket ticket = getTicket(ticketId);

        if(ticket.getCustomer().getCustomerId() != customer.getCustomerId()){
            throw new RuntimeException("Ticket does not belong to customer");
        }

        return ticket;
    }
}