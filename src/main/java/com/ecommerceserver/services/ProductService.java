package com.ecommerceserver.services;

import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Product;

public interface ProductService {
  List<Product> getList(Integer pageNo, Integer pageSize, String sortBy);
  Optional<Product> findById(String productId);
  List<Product> searchByTitle(String title);
  List<Product> addList(List<Product> lstProduct);
}