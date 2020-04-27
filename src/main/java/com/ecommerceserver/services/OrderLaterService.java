package com.ecommerceserver.services;

import java.util.List;

import com.ecommerceserver.model.Product;

public interface OrderLaterService {
  int moveFromCartToOrderLater(String userId, String productId);

  int addBackToCart(String userId, String productId);

  List<Product> getAll(String userId);

  int removeOne(String userId, String productId);

}