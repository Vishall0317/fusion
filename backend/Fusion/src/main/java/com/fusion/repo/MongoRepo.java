package com.fusion.repo;

import com.fusion.model.MongoCollection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepo extends MongoRepository<MongoCollection, String> {
}
