package com.monkdevs.ticketingtool.Services;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import com.monkdevs.ticketingtool.Models.Todo;


public interface TodoService {

    List < Todo > getTodosByName(String name);

    Optional < Todo > getTodoById(long id);

    void updateTodo(Todo todo);

    void addTodo(String name, String desc, Date targetDate, boolean isCompleted);
    void deleteTodo(long id);

    void saveTodo(Todo todo);
}