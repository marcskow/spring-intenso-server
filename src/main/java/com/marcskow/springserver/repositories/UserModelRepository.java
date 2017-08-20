package com.marcskow.springserver.repositories;

import com.marcskow.springserver.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserModelRepository extends MongoRepository<UserModel,String> {
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Optional<UserModel> findOneById(String id);
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Optional<UserModel> findOneByUsername(String username);
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<UserModel> findAll();
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    <S extends UserModel> S save(S user);
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    <S extends UserModel> S insert(S entity);
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(String id);
}
