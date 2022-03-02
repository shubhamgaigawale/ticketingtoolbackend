package com.monkdevs.ticketingtool.Repositories;

import java.util.Optional;

import com.monkdevs.ticketingtool.Models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    User findUserById(Long id);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
