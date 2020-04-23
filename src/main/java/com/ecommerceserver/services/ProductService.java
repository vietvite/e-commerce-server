package com.ecommerceserver.services;

import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

public interface ProductService {
  List<Product> getList(Integer pageNo, Integer pageSize, String sortBy);

  Optional<Product> findById(String productId);

  List<Product> searchByTitle(String title);

  List<Product> addList(List<Product> lstProduct);

  List<Product> getFirst6(String categoryId);

  // List<Product> getProductByCategory(String categoryId, Pageable pageable);

  List<Product> getProduct(Sort sort);
}
