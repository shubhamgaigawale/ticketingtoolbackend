package com.monkdevs.ticketingtool.Controlles;

import java.util.List;
import java.util.Optional;

import com.monkdevs.ticketingtool.Message.Request.AssignTicketRequest;
import com.monkdevs.ticketingtool.Message.Response.ResponseMessage;
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
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        try {
            List<Ticket> tickets = ticketServices.getAlTickets();
            return new ResponseEntity<>(tickets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<Optional<Ticket>> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket =  ticketServices.findById(id);
        if(ticket != null){
            System.out.println(ticket);
            return new ResponseEntity<Optional<Ticket>>(ticket, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{developerTicket}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public Ticket getTicketByDeveloperTicket(@PathVariable String developerTicket) {
        return ticketServices.findByDeveloperTicket(developerTicket);
    }

    @PostMapping("/create")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public Ticket postTicket(@RequestBody Ticket ticket) {
        return ticketServices.createTicket(ticket);
    }

    @DeleteMapping("/delete/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<HttpStatus> deleteTicket(@PathVariable Long id) {
        try {
            ticketServices.deleteTicketById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Long id) {
        Ticket currentTicket = ticketServices.findTicketById(id);
        if (currentTicket != null) {
            ticketServices.updateTicket(ticket, id);
            return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/assignTicket/{ticketId}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<?> assignTicketToUser(@RequestBody AssignTicketRequest assignTicketRequest,
            @PathVariable Long ticketId) {

        if (userServices.existsByEmail(assignTicketRequest.getUserEmailId())) {
            Ticket selectedTicket = ticketServices.findTicketById(ticketId);
            User selectedUser = userServices.getUserByEmail(assignTicketRequest.getUserEmailId());
            if (selectedTicket.getAssignedTo() == null) {
                ticketServices.assignTicketToUser(selectedTicket, selectedUser);
            } else {
                return new ResponseEntity<>(new ResponseMessage("Ticket already assigned"), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ResponseMessage("User does not exist with given email ID"),
                    HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(
                new ResponseMessage("Ticket has been assigned to " + assignTicketRequest.getUserEmailId()),
                HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> reassignTicket(@RequestBody AssignTicketRequest reassignTicketRequest, @PathVariable Long tiicketId){
        if(userServices.existsByEmail(reassignTicketRequest.getUserEmailId())){
            Ticket selectedTicket = ticketServices.findTicketById(tiicketId);
            User newSelectedUser = userServices.getUserByEmail(reassignTicketRequest.getUserEmailId());
            ticketServices.assignTicketToUser(selectedTicket, newSelectedUser);
            return new ResponseEntity<>(new ResponseMessage("Ticket has been reassign to " + reassignTicketRequest.getUserEmailId()), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResponseMessage("User does not exist with given email ID"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/unassignTicket/{ticketId}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<?> unassignTicket(@PathVariable Long ticketId){
        Ticket selectedTicket = ticketServices.findTicketById(ticketId);

        if(selectedTicket != null && selectedTicket.getAssignedTo() != null){
            ticketServices.unassignTicket(selectedTicket);
            return new ResponseEntity<>(new ResponseMessage("Ticket has been unassigned"), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new ResponseMessage("Ticket not assigned to anyone"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/allticketsbyuser/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DEVELOPER') or hasRole('ROLE_TESTER')")
    public ResponseEntity<?> getTicketByUserId(@PathVariable Long id) {
        if(userServices.findUserById(id) != null){
            List<Ticket> ticketsAssignToUser =  ticketServices.getListOfTicketByUser(id);

            if(ticketsAssignToUser != null){
                return new ResponseEntity<>(ticketsAssignToUser, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(new ResponseMessage("This use do not have any tickets"), HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(new ResponseMessage("User does not exist with " + id ), HttpStatus.BAD_REQUEST);
        }
    }
}
