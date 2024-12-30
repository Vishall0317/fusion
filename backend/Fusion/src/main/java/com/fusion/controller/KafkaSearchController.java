package com.fusion.controller;

import com.fusion.model.KafkaRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
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

    @GetMapping("/fetch")
    public List<String> fetchKafkaMessages(@RequestParam String consumerGroup,
                                           @RequestParam String topic) {

        KafkaRequest request = new KafkaRequest();
        request.setTopicName(topic);
        request.setConsumerGroup(consumerGroup);
        log.info("request:: {} ", request);

        return fetchAllMessages(topic, consumerGroup);
    }

    public List<String> fetchAllMessages(String topicName, String consumerGroupId) {
        List<String> messages = new ArrayList<>();

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
                if (records.isEmpty()) {
                    moreMessages = false; // Exit when no more messages are fetched
                }
                for (ConsumerRecord<String, String> record : records) {
                    messages.add(record.value());
                    log.info("Consumer record:: {} ", record);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
}
