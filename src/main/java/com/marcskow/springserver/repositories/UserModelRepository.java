package com.marcskow.springserver.repositories;

import com.marcskow.springserver.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserModelRepository extends MongoRepository<UserModel,String> {
    Optional<UserModel> findOneById(String id);
    Optional<UserModel> findOneByUsername(String username);
    List<UserModel> findAll();
    <S extends UserModel> S save(S user);
    <S extends UserModel> S insert(S entity);
    void delete(String id);
}
