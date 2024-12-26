package com.fusion.controller;

import com.fusion.model.MongoCollection;
import com.fusion.model.MySqlEntity;
import com.fusion.repo.MongoRepo;
import com.fusion.repo.MySqlRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class DataController {

    private final MongoRepo mongoRepo;
    private final MySqlRepo mySqlRepo;

    public DataController(MongoRepo mongoRepo, MySqlRepo mySqlRepo) {
        this.mongoRepo = mongoRepo;
        this.mySqlRepo = mySqlRepo;
    }

    @PostMapping("/mongo")
    public String saveDataToMongo(@RequestBody MongoCollection mongoCollection) {
        mongoRepo.save(mongoCollection);
        return "Saved data in mongo db for id: "+mongoCollection.getId();
    }

    @PostMapping("/mysql")
    public String saveDataToSql(@RequestBody MySqlEntity mySqlEntity) {
        mySqlRepo.save(mySqlEntity);
        return "Saved data in mysql db for id: "+mySqlEntity.getId();
    }

    @GetMapping("/mongo/{id}")
    public Optional<MongoCollection> getMongoDataById(@PathVariable String id) {
        return mongoRepo.findById(id);
    }

    @GetMapping("/mysql/{id}")
    public Optional<MySqlEntity> getMySQLDataById(@PathVariable String id) {
        return mySqlRepo.findById(id);
    }

    @GetMapping("/mongo")
    public List<MongoCollection> getAllMongoData() {
        List<MongoCollection> list = mongoRepo.findAll();
        log.info("getAllMySQLData: "+list);
        return list;
    }

    @GetMapping("/mysql")
    public List<MySqlEntity> getAllMySQLData() {
        List<MySqlEntity> list = mySqlRepo.findAll();
        log.info("getAllMySQLData: "+list);
        return list;
    }
}
