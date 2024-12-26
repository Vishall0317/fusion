package com.fusion.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/mongo")
public class MongoQueryController {

    @GetMapping("/data")
    public List<Document> getData(@RequestParam String dbName,
                                  @RequestParam String collection,
                                  @RequestParam String primaryKey,
                                  @RequestParam String primaryValue) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> coll = database.getCollection(collection);
            Document query = new Document(primaryKey, primaryValue);

            List<Document> results = new ArrayList<>();
            coll.find(query).into(results);
            return results;
        }
    }
}
