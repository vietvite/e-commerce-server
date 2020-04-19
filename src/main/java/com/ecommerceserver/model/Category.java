package com.ecommerceserver.model;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Category {
  @Id
  String id;

  @NotEmpty
  String name;
  @NotEmpty
  String url;
  List<Category> children;

  public Category() {
  }

  public Category(@NotEmpty String name) {
    this.name = name;
  }

  public Category(@NotEmpty String name, @NotEmpty String url) {
    this.name = name;
    this.url = url;
  }
}