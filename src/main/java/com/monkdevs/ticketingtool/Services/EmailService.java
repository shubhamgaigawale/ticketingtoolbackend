package com.monkdevs.ticketingtool.Services;

import com.monkdevs.ticketingtool.Models.User;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendRegistrationEmail(User user, String password){

        String message="\nPlease use the following credentials to log in and edit your personal information including your own password."
				+ "\nUsername:"+user.getUsername()+"\nPassword:"+user.getPassword();

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("monkdevs.shubham@gmail.com");
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Monkdevs Ticket Management System - New User");
        simpleMailMessage.setText(message);
        
        System.out.println("Email has been send");
    }
    
}
