package com.ecommerceserver.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Seller extends User {
  List<Product> listProduct;
}