package com.marcskow.springserver.controllers;

import com.marcskow.springserver.model.todo.TodoList;
import com.marcskow.springserver.services.TodoRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

// todo consider it
// exception handling could be improved as I saw on spring documentation or www.baeldung.com/exception-handling-for-rest-with-spring

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    @Autowired
    private TodoRestService todoRestService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<TodoList> getTodoList(){
        ResponseEntity<String> stringResponseEntity = todoRestService.todoRestCall();

        try {
            return new ResponseEntity<>(todoRestService.buildTodoList(stringResponseEntity.getBody()), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

