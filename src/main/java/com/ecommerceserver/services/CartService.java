package com.ecommerceserver.services;

import java.util.List;

import com.ecommerceserver.model.Product;

public interface CartService {
  int addOne(String userId, String productId);

  int removeOne(String userId, String productId);

  List<Product> getAll(String userId);

  int updateQuantity(String userId, String productId, int quantity);
}