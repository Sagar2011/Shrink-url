package com.shrinkster.urlservice.repository;

import com.shrinkster.urlservice.model.UserCount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserCountRepository extends MongoRepository<UserCount, String> {
     List<UserCount> findByUserId(String userId);
    List<UserCount>  findByUrlId(UUID urlId);
}
