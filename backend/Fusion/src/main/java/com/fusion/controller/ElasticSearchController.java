package com.fusion.controller;

import com.fusion.model.ElasticEntity;
import com.fusion.service.ElasticSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/elastic")
@CrossOrigin(origins = "http://localhost:4200")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping("/createOrUpdate")
    public ResponseEntity<Object> createOrUpdateDocument(@RequestBody ElasticEntity elasticEntity) throws IOException {
        String response = elasticSearchService.createOrUpdateDocument(elasticEntity);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getDocumentById(@RequestParam String id) throws IOException {
        ElasticEntity elasticEntity = elasticSearchService.getDocumentById(id);
        return new ResponseEntity<>(elasticEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteDocumentById(@RequestParam String id) throws IOException {
        String response = elasticSearchService.deleteDocumentById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/searchAll")
    public ResponseEntity<Object> searchAllDocument() throws IOException {
        List<ElasticEntity> elasticEntityList = elasticSearchService.searchAllDocuments();
        return new ResponseEntity<>(elasticEntityList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchDocument(@RequestParam String index, @RequestParam String id) {
        try {
            Object result = elasticSearchService.searchDocument(index, id);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}
