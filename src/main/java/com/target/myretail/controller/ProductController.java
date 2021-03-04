package com.target.myretail.controller;


import com.target.myretail.exceptions.ProductDataNotFoundException;
import com.target.myretail.exceptions.ProductServicesException;
import com.target.myretail.model.Product;
import com.target.myretail.repository.ProductRepository;
import com.target.myretail.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/")
@Slf4j
public class ProductController {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @GetMapping("v1/products")
    @ResponseBody
    public List<Product> getAllProductDetails () {
        return productRepository.findAll();
    }

    // here 'g' represents global search
    @GetMapping(value = "v1/products/g/{id}", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getProductDetailsList (@PathVariable("id")String id) {
        log.info("Getting All the Product Details");
        CompletableFuture<List<Product>> aggProdcutDetails=  productService.productDetailsAggregator(id);
        return aggProdcutDetails.join();
    }


    @GetMapping(value = "v1/products/{id}", produces = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct (@PathVariable("id")String id) {
        log.info("Getting All the Product Details");
        Product productMyRetail = productRepository.findProductById(id);
        if(productMyRetail == null) throw new ProductDataNotFoundException("Product-Id-"+id+ " is Not found");
        return productMyRetail;
    }

    @PutMapping("v1/products/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct (@Valid @RequestBody Product product) throws ProductServicesException{
        log.info("Updating - "+product.getName()+" Details");
       return productRepository.save(product);
    }

    @PostMapping(value = "v1/products", consumes = "application/json")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct (@Valid @RequestBody Product product) throws ProductServicesException {
        log.info("Adding a new product - "+product.getName());
        return productRepository.save(product);
    }
}