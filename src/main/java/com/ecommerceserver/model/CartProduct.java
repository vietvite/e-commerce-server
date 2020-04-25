package com.ecommerceserver.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "product")
@Data
@NoArgsConstructor
public class CartProduct extends Product {
  int quantity = 1;

  public CartProduct(String id, String title, Long price, String description, int stock, String imageUrl,
      Category category, Seller seller, ReviewStar reviewStar, Date createAt, Date updateAt, int quantity) {
    super(id, title, price, description, stock, imageUrl, category, seller, reviewStar, createAt, updateAt);
    this.quantity = quantity;
  }

  public CartProduct(int quantity) {
    this.quantity = quantity;
  }
}