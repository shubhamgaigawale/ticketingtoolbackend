package com.monkdevs.ticketingtool.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monkdevs.ticketingtool.Models.Todo;

public interface TodoRepository extends JpaRepository < Todo, Long > {
   
	static List < Todo > findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
}