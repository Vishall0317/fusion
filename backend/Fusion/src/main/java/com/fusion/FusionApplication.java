package com.fusion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories(basePackages = "com.fusion.repo")
public class FusionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FusionApplication.class, args);
	}

}
