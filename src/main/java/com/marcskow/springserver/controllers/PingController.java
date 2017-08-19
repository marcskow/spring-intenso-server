package com.marcskow.springserver.controllers;


import com.marcskow.springserver.model.Note;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ping")
public class PingController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Note> ping(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

