package com.messaginapps.simplemessaging.repository;

import com.messaginapps.simplemessaging.model.Status;
import com.messaginapps.simplemessaging.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByStatus(Status status);
}
