//package com.fusion.controller;
//
//import com.fusion.model.ElasticEntity;
//import com.fusion.repo.ElasticRepo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Slf4j
//@RestController
//@RequestMapping("/elastic")
//@CrossOrigin(origins = "http://localhost:4200")
//public class ElasticSearchQuery {
//
//    @Autowired
//    private ElasticRepo elasticRepo;
//
//    @PostMapping("/save")
//    public ElasticEntity saveEmployee(@RequestBody ElasticEntity elasticEntity) {
//        return elasticRepo.save(elasticEntity);
//    }
//
//    @GetMapping("/search/name/{name}")
//    public List<ElasticEntity> searchByName(@PathVariable String name) {
//        return elasticRepo.findByName(name);
//    }
//
//    @GetMapping("/search/id/{id}")
//    public ElasticEntity searchById(@PathVariable String id) {
//        return elasticRepo.findById(id).orElse(null);
//    }
//}
