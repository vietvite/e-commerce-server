package com.ecommerceserver.respository;

import java.util.List;
import java.util.stream.Stream;

import com.ecommerceserver.model.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface ProductRepository extends MongoRepository<Product, String> {
  List<Product> findTop6ByCategoryId(String categoryId);

  List<Product> findByCategoryId(String categoryId, Sort sort);

  List<Product> findByTitleLike(String search);
}
