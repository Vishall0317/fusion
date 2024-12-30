package com.fusion.model;

import lombok.Data;

@Data
public class KafkaRequest {

//    private String regex;
    private String consumerGroup;
    private String topicName;
    private int partition;
    private long offset;
}
