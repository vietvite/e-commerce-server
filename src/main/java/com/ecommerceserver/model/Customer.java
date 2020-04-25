package com.ecommerceserver.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {

  List<CartProduct> listCart;
  List<CartProduct> listFavorite;
  List<CartProduct> listOrderLater;
}