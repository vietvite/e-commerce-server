package com.ecommerceserver.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Product {
  @Id
  String id;

  String title;
  BigDecimal price;
  String description;
  int stock;
  String imageUrl;

  Category category;
  Seller seller;
  ReviewStar reviewStar;
}