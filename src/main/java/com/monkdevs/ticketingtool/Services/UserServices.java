package com.monkdevs.ticketingtool.Services;

import java.util.List;
import java.util.Optional;

import com.monkdevs.ticketingtool.Models.User;

public interface UserServices {
    
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    List<User> getAllUsers();
}
