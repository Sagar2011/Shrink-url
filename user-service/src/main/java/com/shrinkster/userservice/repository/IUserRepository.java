package com.shrinkster.userservice.repository;

import com.shrinkster.userservice.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User,String> {
    User findByEmail(String email);
}
