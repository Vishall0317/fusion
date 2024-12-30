package com.fusion.model;

import lombok.Data;

@Data
public class KafkaResponse {

    private String key;
    private String value;

    public KafkaResponse(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
