package com.ecommerceserver.services;

import java.util.List;

import com.ecommerceserver.model.Product;

public interface CartService {
  int setCart(String userId, Product product);

  List<Product> getCart(String userId);
}