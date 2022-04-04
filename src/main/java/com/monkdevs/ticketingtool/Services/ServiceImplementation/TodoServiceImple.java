package com.monkdevs.ticketingtool.Services.ServiceImplementation;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monkdevs.ticketingtool.Models.Todo;
import com.monkdevs.ticketingtool.Repositories.TodoRepository;
import com.monkdevs.ticketingtool.Services.TodoService;

@Service
public class TodoServiceImple implements TodoService {

    @Autowired
    private TodoRepository todoRepository;
	
    @Override
    public List < Todo > getTodosByName(String name) {
        return TodoRepository.findByName(name);
    }

    @Override
    public Optional < Todo > getTodoById(long id) {
        return todoRepository.findById(id);
    }
    @Override
    public void updateTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void addTodo(String name, String desc, Date targetDate, boolean isCompleted) {
        todoRepository.save(new Todo(name, desc, targetDate, isCompleted));
    }

    @Override
    public void deleteTodo(long id) {
        Optional < Todo > todo = todoRepository.findById(id);
        if (todo.isPresent()) {
            todoRepository.delete(todo.get());
        }
    }
    @Override
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }
}