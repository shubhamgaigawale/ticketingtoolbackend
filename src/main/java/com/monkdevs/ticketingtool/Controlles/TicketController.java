package com.monkdevs.ticketingtool.Controlles;

import java.util.List;

import com.monkdevs.ticketingtool.Models.Ticket;
import com.monkdevs.ticketingtool.Services.TicketServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    
    @Autowired
    private TicketServices ticketServices;

    @GetMapping("/all")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        try{
            List<Ticket> tickets = ticketServices.getAlTickets();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/create")
    public Ticket postTicket(@RequestBody Ticket ticket){
        return ticketServices.createTicket(ticket);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable Long id){
        try{
            ticketServices.deleteTicketById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
