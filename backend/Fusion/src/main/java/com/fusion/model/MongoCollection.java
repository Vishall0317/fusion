package com.fusion.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fusion")
@Data
public class MongoCollection {

    @Id
    private String id;
    private String name;
    private String city;
    private String salary;
}
