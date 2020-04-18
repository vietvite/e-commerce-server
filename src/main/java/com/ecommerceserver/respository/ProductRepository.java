package com.ecommerceserver.respository;

import com.ecommerceserver.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}