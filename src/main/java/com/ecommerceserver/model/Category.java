package com.ecommerceserver.model;

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

  public Category() {
  }

  public Category(@NotEmpty String name) {
    this.name = name;
  }

}