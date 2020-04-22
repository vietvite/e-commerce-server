package com.ecommerceserver.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

  List<Product> listCart;
  List<Product> listFavorite;
  List<Product> listOrderLater;
}