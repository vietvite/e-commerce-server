package com.ecommerceserver.services;

import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Product;

public interface ProductService {
  List<Product> getListProduct(Integer pageNo, Integer pageSize, String sortBy);
  Optional<Product> findProductById(String productId);
  List<Product> addListProduct(List<Product> lstProduct);
}