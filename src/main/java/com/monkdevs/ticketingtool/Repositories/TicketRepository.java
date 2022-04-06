package com.monkdevs.ticketingtool.Repositories;

import java.util.List;
import java.util.Optional;

import com.monkdevs.ticketingtool.Models.Ticket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
    
    Ticket findTicketById(Long id);

    Optional<Ticket> findById(Long id);

    @Query(value = "SELECT * FROM TICKET t WHERE t.developer_ticket = :developerTicket", nativeQuery = true)
    Ticket getByDeveloperTicket(String developerTicket);

    Ticket findByTicketName(String ticketName);

    @Query(value = "SELECT * FROM TICKET t WHERE t.assigned_to_id = :id", nativeQuery = true)
    List<Ticket> findTicketByUser(Long id);
}
