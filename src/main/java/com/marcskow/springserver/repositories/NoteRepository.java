package com.marcskow.springserver.repositories;

import com.marcskow.springserver.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends MongoRepository<Note,String> {
    Optional<Note> findOneById(String id);
    List<Note> findAll();
    <S extends Note> S save(S note);
    <S extends Note> S insert(S entity);
    void delete(String id);
}
