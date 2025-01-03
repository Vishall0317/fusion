package com.fusion.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.fusion.model.ElasticEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ElasticSearchQuery {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    private final String indexName = "products";


    public String createOrUpdateDocument(ElasticEntity product) throws IOException {

        IndexResponse response = elasticsearchClient.index(i -> i
                .index(indexName)
                .id(product.getId())
                .document(product)
        );
        if(response.result().name().equals("Created")){
            return new StringBuilder("Document has been successfully created.").toString();
        }else if(response.result().name().equals("Updated")){
            return new StringBuilder("Document has been successfully updated.").toString();
        }
        return new StringBuilder("Error while performing the operation.").toString();
    }

    public ElasticEntity getDocumentById(String productId) throws IOException{
        ElasticEntity product = null;
        GetResponse<ElasticEntity> response = elasticsearchClient.get(g -> g
                        .index(indexName)
                        .id(productId),
                ElasticEntity.class
        );

        if (response.found()) {
            product = response.source();
            System.out.println("Product name " + product.getName());
        } else {
            System.out.println ("Product not found");
        }

        return product;
    }

    public String deleteDocumentById(String productId) throws IOException {

        DeleteRequest request = DeleteRequest.of(d -> d.index(indexName).id(productId));

        DeleteResponse deleteResponse = elasticsearchClient.delete(request);
        if (Objects.nonNull(deleteResponse.result()) && !deleteResponse.result().name().equals("NotFound")) {
            return new StringBuilder("Product with id " + deleteResponse.id() + " has been deleted.").toString();
        }
        System.out.println("Product not found");
        return new StringBuilder("Product with id " + deleteResponse.id()+" does not exist.").toString();

    }

    public  List<ElasticEntity> searchAllDocuments() throws IOException {

        SearchRequest searchRequest =  SearchRequest.of(s -> s.index(indexName));
        SearchResponse searchResponse =  elasticsearchClient.search(searchRequest, ElasticEntity.class);
        List<Hit> hits = searchResponse.hits().hits();
        List<ElasticEntity> products = new ArrayList<>();
        for(Hit object : hits){

            System.out.print(((ElasticEntity) object.source()));
            products.add((ElasticEntity) object.source());

        }
        return products;
    }
}
