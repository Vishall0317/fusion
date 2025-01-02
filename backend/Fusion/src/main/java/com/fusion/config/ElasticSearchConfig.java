//package com.fusion.config;
//
//import co.elastic.clients.elasticsearch.ElasticsearchClient;
//import co.elastic.clients.transport.rest_client.RestClientTransport;
//import co.elastic.clients.json.jackson.JacksonJsonpMapper;
//import org.apache.http.HttpHost;
//import org.elasticsearch.client.RestClient;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//
//
//@Configuration
//public class ElasticSearchConfig {
//
//
//    @Bean
//    public ElasticsearchClient elasticsearchClient() {
//        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
//        RestClientTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
//        return new ElasticsearchClient(transport);
//    }
//
//    @Bean
//    public ElasticsearchOperations elasticsearchTemplate(ElasticsearchClient client) {
//        return new ElasticsearchTemplate(client);
//    }
//}