package com.monkdevs.ticketingtool.Services.ServiceImplementation;

import java.util.List;
import java.util.Optional;

import com.monkdevs.ticketingtool.Models.User;
import com.monkdevs.ticketingtool.Repositories.UserRepository;
import com.monkdevs.ticketingtool.Services.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserServices{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
    
}
