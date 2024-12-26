package com.fusion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/database")
public class DynamicQueryController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/data")
    public List<Map<String, Object>> getData(@RequestParam String dbName,
                                             @RequestParam String tableName,
                                             @RequestParam String primaryKey,
                                             @RequestParam String id) {
        // Construct dynamic SQL query
        String sql = String.format("SELECT * FROM %s.%s WHERE %s = ?", dbName, tableName, primaryKey);

        // Execute query and return results
        return jdbcTemplate.queryForList(sql, id);
    }
}
