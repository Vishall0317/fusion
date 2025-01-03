package com.fusion.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fusion.model.ElasticEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class ElasticSearchService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "fusion_es";

    public String createOrUpdateDocument(ElasticEntity elasticEntity) throws IOException {
        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(elasticEntity.getId())
                .document(elasticEntity)
        );
        if (response.result().name().equals("Created")) {
            return new StringBuilder("Document has been successfully created.").toString();
        } else if (response.result().name().equals("Updated")) {
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public ElasticEntity getDocumentById(String id) throws IOException {
        ElasticEntity elasticEntity = null;
        GetResponse<ElasticEntity> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(id),
                ElasticEntity.class
        );
        if (response.found()) {
            elasticEntity = response.source();
            System.out.println("Document: " + elasticEntity);
        } else {
            System.out.println("Document not found");
        }
        return elasticEntity;
    }

    public String deleteDocumentById(String id) throws IOException {
        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(id));
        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Document with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        log.info("Document not found");
        return new StringBuilder("Document with id " + deleteResponse.id() + " does not exist.").toString();
    }

    public List<ElasticEntity> searchAllDocuments() throws IOException {
        SearchRequest searchRequest = SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse = elasticsearchClient.search(searchRequest, ElasticEntity.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticEntity> elasticEntityList = new ArrayList<>();
        for (Hit object : hits) {
            log.info("Document: " + ((ElasticEntity) object.source()));
            elasticEntityList.add((ElasticEntity) object.source());
        }
        return elasticEntityList;
    }

    public Object searchDocument(String index, String id) throws Exception {
        GetResponse<Object> response = elasticsearchClient.get(g -> g
                        .index(index)
                        .id(id),
                Object.class);
        if (response.found()) {
            log.info("Document found: " + response.source());
            return response.source();
        } else {
            log.info("Document not found");
            throw new Exception("Document not found in index: " + index + ", id: " + id);
        }
    }
}
