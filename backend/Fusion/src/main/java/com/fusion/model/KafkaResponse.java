package com.fusion.model;

import lombok.Data;

@Data
public class KafkaResponse {

    private String key;
    private String value;
    private String topic;
    private int partition;
    private long offset;
    private String timestamp;
}
