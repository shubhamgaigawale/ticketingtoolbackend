package com.monkdevs.ticketingtool.Services;

import java.util.List;
import java.util.Optional;

import com.monkdevs.ticketingtool.Models.User;

public interface UserServices {
    
    Optional<User> findByUsername(String username);

    User findUserById(Long id);

    List<User> getAllUsers();
}
