package com.marcskow.springserver.controllers;

import com.marcskow.springserver.model.Task;
import com.marcskow.springserver.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> task = taskRepository.findAll();
        if(task.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Task> getTaskById(@PathVariable("id") String id){
        Optional<Task> task = taskRepository.findOneById(id);
        return task.map(task1 -> new ResponseEntity<>(task1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addTask(@RequestBody Task task, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(new Error(errors.toString()),HttpStatus.BAD_REQUEST);
        }
        Task result = taskRepository.save(task);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Error("Error"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateTask(@RequestBody Task task, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(new Error(errors.toString()),HttpStatus.BAD_REQUEST);
        }
        Task result = taskRepository.insert(task);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Error("Error"), HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTask(@PathVariable("id") String id) {
        if(taskRepository.findOneById(id) != null) {
            taskRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
