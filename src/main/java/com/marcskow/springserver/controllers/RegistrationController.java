package com.marcskow.springserver.controllers;

import com.marcskow.springserver.model.UserModel;
import com.marcskow.springserver.repositories.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sign-up")
public class RegistrationController {

    @Autowired
    private UserModelRepository userModelRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUserModel(@RequestBody UserModel userModel, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(new Error(errors.toString()),HttpStatus.BAD_REQUEST);
        }
        userModel.grantPrivilage("ROLE_BASIC");
        UserModel result = userModelRepository.save(userModel);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Error("Error"), HttpStatus.BAD_REQUEST);
        }
    }
}
