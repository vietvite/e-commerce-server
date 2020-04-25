package com.ecommerceserver.respository;

import com.ecommerceserver.model.Category;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
  Category findByName(String name);
}