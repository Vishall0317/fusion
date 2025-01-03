package com.fusion.model;

import lombok.Data;

@Data
public class KafkaResponse {

    private String key;
    private Object value;
    private String topic;
    private int partition;
    private long offset;
    private String timestamp;
}
