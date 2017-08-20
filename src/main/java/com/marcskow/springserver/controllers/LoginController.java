package com.marcskow.springserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign-in")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> login(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
