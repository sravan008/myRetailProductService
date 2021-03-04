package com.target.myretail.service;

import com.target.myretail.config.ProductConfiguration;
import com.target.myretail.exceptions.ProductDataNotFoundException;
import com.target.myretail.model.Product;
import com.target.myretail.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductConfiguration productConfiguration;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ProductRepository productRepository;

    @Async
    public CompletableFuture<List<Product>> productDetailsAggregator(String productId) throws ProductDataNotFoundException {
       List<String> endpoints = generateUrlWithParameters(productId);
       List<Product> aggProductList = new LinkedList<>();
        Product responseObj = null;
        for (String endpointUrl: endpoints) {
           try {
               responseObj = restTemplate.getForObject(endpointUrl, Product.class);// Get the details from all the other endpoints
           }catch (Exception exception){
               log.error("Product Id is not found in "+ endpointUrl);
           }
           aggProductList.add(responseObj);
        }
        Product productMyRetail = productRepository.findProductById(productId); // Get the product details from DB
        if(productMyRetail!=null)aggProductList.add(productMyRetail); // Aggregate the external endpoints data + DB result;
        return CompletableFuture.completedFuture(aggProductList);
    }

    // This Method is responsible for generating the URL's based upon the Endpoint's.
    // Example: https://api.target.com/products/v3/  ->> It will generate the point https://api.target.com/products/v3/136532
    // Currently I have mentioned the endpoints in the property file, we can get the endpoints from service registry in cloud native architecture
    List<String> generateUrlWithParameters(String productId){
        List<String> endpoints = new LinkedList<>();
        for (String endpointUrl:productConfiguration.getEndpoints()){
            StringBuilder urlAppender = new StringBuilder(endpointUrl);
            endpoints.add(urlAppender.append(productId).toString());
        }
        return endpoints;
    }

}