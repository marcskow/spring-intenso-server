package com.marcskow.springserver.controllers;


import com.marcskow.springserver.model.UserModel;
import com.marcskow.springserver.repositories.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserModelRepository userModelRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserModel>> getUserModel(){
        List<UserModel> userModel = userModelRepository.findAll();
        if(userModel.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(userModel, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserModel> getUserModelByUserName(@PathVariable("username") String username){
        Optional<UserModel> userModel = userModelRepository.findOneByUsername(username);
        return userModel.map(userModel1 -> new ResponseEntity<>(userModel1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addUserModel(@RequestBody UserModel userModel, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(new Error(errors.toString()),HttpStatus.BAD_REQUEST);
        }
        UserModel result = userModelRepository.save(userModel);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Error("Error"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateUserModel(@RequestBody UserModel userModel, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(new Error(errors.toString()),HttpStatus.BAD_REQUEST);
        }
        UserModel result = userModelRepository.insert(userModel);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Error("Error"), HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUserModel(@PathVariable("id") String id) {
        if(userModelRepository.findOneById(id) != null) {
            userModelRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
