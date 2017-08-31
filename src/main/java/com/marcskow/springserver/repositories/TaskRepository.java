package com.marcskow.springserver.repositories;

import com.marcskow.springserver.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends MongoRepository<Task,String> {
    Optional<Task> findOneById(String id);
    List<Task> findAll();
    <S extends Task> S save(S task);
    <S extends Task> S insert(S entity);
    void delete(String id);
}
