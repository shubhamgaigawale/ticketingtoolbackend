package com.monkdevs.ticketingtool.Repositories;

import com.monkdevs.ticketingtool.Models.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    
    Ticket findTicketById(Long id);

    Ticket findByTicketId(String ticketId);

    Ticket findByTicketName(String ticketName);
}
