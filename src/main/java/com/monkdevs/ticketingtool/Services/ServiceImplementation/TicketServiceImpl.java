package com.monkdevs.ticketingtool.Services.ServiceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.monkdevs.ticketingtool.Models.Ticket;
import com.monkdevs.ticketingtool.Models.User;
import com.monkdevs.ticketingtool.Repositories.TicketRepository;
import com.monkdevs.ticketingtool.Services.TicketServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketServices{

    @Autowired
    private TicketRepository ticketRepository;


    public String getRandomNumberString(){
        Random random = new Random();
        int number = random.nextInt(999999);
        return String.format("%06d", number);
    }

    @Override
    public Ticket findTicketById(Long id) {
        return ticketRepository.findTicketById(id);
    }


    @Override
    public Ticket findByDeveloperTicket(String developerTicket) {
        return ticketRepository.getByDeveloperTicket(developerTicket);
    }

    @Override
    public Ticket findByTicketName(String ticketName) {
        return ticketRepository.findByTicketName(ticketName);
    }

    @Override
    public List<Ticket> getAlTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        if(ticket.getTicketType().equals("supportTicket")){
            ticket.setDeveloperTicket("T-" + getRandomNumberString());
        }else{
            ticket.setDeveloperTicket("DT-" + getRandomNumberString());
        }
        ticket.setStatus("pending");
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket, Long id) {
        Ticket existingTicket = ticketRepository.findTicketById(id);

        existingTicket.setTicketName(ticket.getTicketName());
        existingTicket.setTicketDescription(ticket.getTicketDescription());
        existingTicket.setTicketType(ticket.getTicketType());
        existingTicket.setPriority(ticket.getPriority());
        existingTicket.setStepsToReproduce(ticket.getStepsToReproduce());
        existingTicket.setActive(ticket.isActive());
        existingTicket.setStatus(ticket.getStatus());
        existingTicket.setRegressions(ticket.isRegressions());
        existingTicket.setApplicationName(ticket.getApplicationName());

        return ticketRepository.save(existingTicket);
    }

    @Override
    public void deleteTicketById(Long id) {
        ticketRepository.deleteById(id);        
    }

    @Override
    public void assignTicketToUser(Ticket ticket, User user) {
        ticket.setAssignedTo(user);
        ticketRepository.save(ticket);        
    }

    @Override
    public void unassignTicket(Ticket ticket) {
        ticket.setAssignedTo(null);
        ticketRepository.save(ticket);
    }

    @Override
    public List<Ticket> getListOfTicketByUser(Long userId) {
        return ticketRepository.findTicketByUser(userId);
    }

    @Override
    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }
}
