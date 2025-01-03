package com.fusion.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

//@Document(indexName = "fusion_es", shards = 1, replicas = 0, refreshInterval = "-1")
@Document(indexName = "fusion_es")
@Data
public class ElasticEntity {

    @Id
    private String id;
    private String name;
    private String city;
    private String salary;
}
