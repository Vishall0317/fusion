package com.fusion.controller;

import com.fusion.model.KafkaRequest;
import com.fusion.model.KafkaResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
@RestController
@RequestMapping("/kafka")
@CrossOrigin(origins = "http://localhost:4200")
public class KafkaSearchController {

//    @GetMapping("/fetch")
//    public List<String> fetchKafkaMessages(@RequestParam String consumerGroup,
//                                                  @RequestParam String topic) {
//
//        KafkaRequest request = new KafkaRequest();
//        request.setTopicName(topic);
//        request.setConsumerGroup(consumerGroup);
//        log.info("request:: {} ", request);
//
//        return fetchAllMessages(topic, consumerGroup);
//    }


    @GetMapping("/fetch")
    public ResponseEntity<List<KafkaResponse>> fetchKafkaMessages(@RequestParam String consumerGroup,
                                                                  @RequestParam String topic) {
        // Validate parameters
        if (consumerGroup == null || topic == null || consumerGroup.isEmpty() || topic.isEmpty()) {
            log.info("consumerGroup or topic must not be null: {}, {}", consumerGroup, topic);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        KafkaRequest request = new KafkaRequest();
        request.setTopicName(topic);
        request.setConsumerGroup(consumerGroup);
        log.info("request:: {} ", request);
//        ConsumerRecords<String, String> records = fetchAllMessages(topic, consumerGroup);
//        List<KafkaResponse> kafkaResponses = new ArrayList<>();
//        for (ConsumerRecord<String, String> record : records) {
//            log.info("Consumer records:: {} ", record);
//            KafkaResponse kafkaResponse = new KafkaResponse();
//            kafkaResponse.setKey(record.key());
//            kafkaResponse.setValue(record.value());
//            kafkaResponse.setTopic(record.topic());
//            kafkaResponse.setPartition(record.partition());
//            kafkaResponse.setOffset(record.offset());
//            kafkaResponse.setTimestamp(String.valueOf(System.currentTimeMillis()));
//            kafkaResponses.add(kafkaResponse);
//        }
        return ResponseEntity.ok(fetchAllMessages(topic, consumerGroup));
    }

//    @GetMapping("/search")
//    public List<KafkaResponse> searchKafkaMessages(@RequestParam String consumerGroup,
//                                            @RequestParam String topic,
//                                            @RequestParam String partition,
//                                            @RequestParam String offset) {
//
//        KafkaRequest request = new KafkaRequest();
//        request.setTopicName(topic);
//        request.setConsumerGroup(consumerGroup);
//        log.info("request:: {} ", request);
//        ConsumerRecords<String, String> records = fetchAllMessages(topic, consumerGroup);
//        List<KafkaResponse> kafkaResponses = new ArrayList<>();
//        for (ConsumerRecord<String, String> record : records) {
//            log.info("Consumer records:: {} ", record);
//            if(String.valueOf(record.partition()).equals(partition) && String.valueOf(record.offset()).equals(offset)){
//                KafkaResponse kafkaResponse = new KafkaResponse();
//                kafkaResponse.setKey(record.key());
//                kafkaResponse.setValue(record.value());
//                kafkaResponse.setTopic(record.topic());
//                kafkaResponse.setPartition(record.partition());
//                kafkaResponse.setOffset(record.offset());
//                kafkaResponse.setTimestamp(String.valueOf(System.currentTimeMillis()));
//                kafkaResponses.add(kafkaResponse);
//            }
//        }
//        return kafkaResponses;
//    }

    public List<KafkaResponse> fetchAllMessages(String topicName, String consumerGroupId) {
        List<KafkaResponse> kafkaResponses = new ArrayList<>();

        // Kafka Consumer Properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka broker
        props.put("group.id", consumerGroupId); // Temporary group ID to fetch all messages
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest"); // Start from the beginning
        props.put("enable.auto.commit", "false"); // Manual offset management

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {

            // Assign topic partitions manually
            List<TopicPartition> partitions = new ArrayList<>();
            consumer.partitionsFor(topicName).forEach(info ->
                    partitions.add(new TopicPartition(topicName, info.partition()))
            );
            consumer.assign(partitions);

            // Seek to the beginning of each partition
            for (TopicPartition partition : partitions) {
                consumer.seekToBeginning(Collections.singletonList(partition));
            }

            // Poll messages from topic
            boolean moreMessages = true;
            while (moreMessages) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                log.info("Consumer records1:: {} ", records);
                if (records.isEmpty()) {
                    moreMessages = false; // Exit when no more messages are fetched
                }
//                for (ConsumerRecord<String, String> record : records) {
//                    messages.add(record.value());
//                    log.info("Consumer record:: {} ", record);
//                }
                for (ConsumerRecord<String, String> record : records) {
                    log.info("Consumer record:: {} ", record);
                    KafkaResponse kafkaResponse = new KafkaResponse();
                    kafkaResponse.setKey(record.key());
                    kafkaResponse.setValue(record.value());
                    kafkaResponse.setTopic(record.topic());
                    kafkaResponse.setPartition(record.partition());
                    kafkaResponse.setOffset(record.offset());
                    kafkaResponse.setTimestamp(String.valueOf(System.currentTimeMillis()));
                    kafkaResponses.add(kafkaResponse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kafkaResponses;
    }
}
