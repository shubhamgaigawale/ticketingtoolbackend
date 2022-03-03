package com.monkdevs.ticketingtool.Controlles;

import java.util.List;

import com.monkdevs.ticketingtool.Models.Ticket;
import com.monkdevs.ticketingtool.Models.User;
import com.monkdevs.ticketingtool.Services.TicketServices;
import com.monkdevs.ticketingtool.Services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/ticket")
public class TicketController {
    
    @Autowired
    private TicketServices ticketServices;

    @Autowired
    private UserServices userServices;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<List<Ticket>> getAllTickets(){
        try{
            List<Ticket> tickets = ticketServices.getAlTickets();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public Ticket getTicketById(@PathVariable Long id){
        return ticketServices.findTicketById(id);
    }

    @PostMapping("/create")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public Ticket postTicket(@RequestBody Ticket ticket){
        return ticketServices.createTicket(ticket);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable Long id){
        try{
            ticketServices.deleteTicketById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Long id){
        Ticket currentTicket = ticketServices.findTicketById(id);
            if(currentTicket != null){
                ticketServices.updateTicket(ticket, id);
                return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
    }

    @GetMapping("/assignTicket/{userId}/{ticketId}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public String assignTicketToUser(@PathVariable Long userId, @PathVariable Long ticketId){
        Ticket selectedTicket = ticketServices.findTicketById(ticketId);
        User selectedUser = userServices.findUserById(userId);
        ticketServices.assignTicketToUser(selectedTicket, selectedUser);
        return "Ticket has been assigned";
    }

    @GetMapping("/allticketsbyuser/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public List<Ticket> getTicketByUserId(@PathVariable Long id){
        return ticketServices.getListOfTicketByUser(id);
    }
}
