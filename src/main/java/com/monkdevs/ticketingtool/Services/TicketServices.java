package com.monkdevs.ticketingtool.Services;

import java.util.List;

import com.monkdevs.ticketingtool.Models.Ticket;
import com.monkdevs.ticketingtool.Models.User;

public interface TicketServices {

    public Ticket findTicketById(Long id);

    public Ticket findByTicketId(String ticketId);

    public Ticket findByTicketName(String ticketName);

    public List<Ticket> getAlTickets();

    public Ticket createTicket(Ticket ticket);

    public Ticket updateTicket(Ticket ticket, Long id);

    public void deleteTicketById(Long id);

    public void assignTicketToUser(Ticket ticket, User selectedUser);

    public void unassignTicket(Ticket ticket);

    public List<Ticket> getListOfTicketByUser(Long userId);
}
