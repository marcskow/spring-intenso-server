package com.marcskow.springserver.controllers;

import com.marcskow.springserver.model.Note;
import com.marcskow.springserver.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/note")
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Note>> getNote(){
        List<Note> note = noteRepository.findAll();
        if(note.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(note, HttpStatus.OK);
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Note> getNoteById(@PathVariable("id") String id){
        Optional<Note> note = noteRepository.findOneById(id);
        return note.map(note1 -> new ResponseEntity<>(note1, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNote(@RequestBody Note note, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(new Error(errors.toString()),HttpStatus.BAD_REQUEST);
        }
        Note result = noteRepository.save(note);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(new Error("Error"), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateNote(@RequestBody Note note, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(new Error(errors.toString()),HttpStatus.BAD_REQUEST);
        }
        Note result = noteRepository.insert(note);
        if(result != null){
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Error("Error"), HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteNote(@PathVariable("id") String id) {
        if(noteRepository.findOneById(id) != null) {
            noteRepository.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
