package com.monkdevs.ticketingtool.Controlles;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monkdevs.ticketingtool.Models.Todo;
import com.monkdevs.ticketingtool.Services.TodoService;


@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    
   
    
        @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
        public String deleteTodo(@RequestParam long id) {
            todoService.deleteTodo(id);
            // service.deleteTodo(id);
            return "redirect:/list-todos";
        }
        @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
        public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
            Todo todo = todoService.getTodoById(id).get();
            model.put("todo", todo);
            return "todo";
        }
        @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
        public String updateTodo(ModelMap model, @Validated Todo todo, BindingResult result) {

            if (result.hasErrors()) {
                return "todo";
            }

            
            todoService.updateTodo(todo);
            return "redirect:/list-todos";
        }
        @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
        public String addTodo(ModelMap model, @Validated Todo todo, BindingResult result) {

            if (result.hasErrors()) {
                return "todo";
            }

            
            todoService.saveTodo(todo);
            return "redirect:/list-todos";
        }
    }
        

