package com.target.myretail.repository;


import com.target.myretail.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface ProductRepository extends MongoRepository<Product, String> {
    public Product findProductById(String id);
}