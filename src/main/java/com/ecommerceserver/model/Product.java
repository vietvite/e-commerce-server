package com.ecommerceserver.model;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Product {
  @Id
  String id;

  String title;
  Long price;
  String description;
  int stock;
  String imageUrl;

  Category category;
  Seller seller;
  ReviewStar reviewStar;

  Date createAt;
  Date updateAt;

  public int getAvarageStar() {
    ReviewStar stars = this.getReviewStar();
    float totalStars = stars._1star * 1 + stars._2star * 2 + stars._3star * 3 + stars._4star * 4 + stars._5star * 5;
    float totalReviews = stars._1star + stars._2star + stars._3star + stars._4star + stars._5star;
    return Math.round(totalStars / totalReviews);
  }
}
