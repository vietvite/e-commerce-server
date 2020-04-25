package com.ecommerceserver.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "product")
@AllArgsConstructor
@NoArgsConstructor
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

  @JsonIgnore
  public int getAvarageStar() {
    ReviewStar stars = this.getReviewStar();
    float totalStars = stars._1star * 1 + stars._2star * 2 + stars._3star * 3 + stars._4star * 4 + stars._5star * 5;
    float totalReviews = stars._1star + stars._2star + stars._3star + stars._4star + stars._5star;
    return Math.round(totalStars / totalReviews);
  }

  public Product(String title, Long price, String description, int stock, String imageUrl, Category category,
      Seller seller, ReviewStar reviewStar, Date createAt, Date updateAt) {
    this.title = title;
    this.price = price;
    this.description = description;
    this.stock = stock;
    this.imageUrl = imageUrl;
    this.category = category;
    this.seller = seller;
    this.reviewStar = reviewStar;
    this.createAt = createAt;
    this.updateAt = updateAt;
  }
}
