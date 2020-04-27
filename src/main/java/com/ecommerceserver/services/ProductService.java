package com.ecommerceserver.services;

import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;

public interface ProductService {
  List<Product> getList(Integer pageNo, Integer pageSize, String sortBy);

  List<Product> findAll();

  Optional<Product> findById(String productId);

  Product deleteById(String productId);

  List<Product> searchByTitle(String title);

  Product addProduct(Product product);

  Product editProduct(Product product);

  List<Product> getFirst6(String categoryId);

  List<Product> findBySellerId(String sellerId);

  List<Product> getProduct(Sort sort);
}
