package com.shrinkster.urlservice.repository;

import com.shrinkster.urlservice.model.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
    List<Url> findByUserId(String userId);
}
