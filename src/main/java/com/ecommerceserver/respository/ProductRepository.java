package com.ecommerceserver.respository;

import java.util.List;

import com.ecommerceserver.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
  List<Product> findTop6ByCategoryId(String categoryId);
}
