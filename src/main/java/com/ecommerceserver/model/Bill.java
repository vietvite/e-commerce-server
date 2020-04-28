package com.ecommerceserver.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

  @Id
  String id = new ObjectId().toString();
  List<CartProduct> listProduct;
  Address deliveryAddress;
  Date orderDate = new Date();
  Date deliveryDate = new Date();

  
  /**
   * Delivery status
   * 
   * Pending: -1
   * 
   * Delivering: 0
   * 
   * Delivered: 1
   */
  int status;


  public Bill(List<CartProduct> listProduct, Address deliveryAddress) {
    this.listProduct = listProduct;
    this.deliveryAddress = deliveryAddress;
  }
}

// Bill customer = new Bill(); => set history customer

// customer => listProduct => for

// sellter => new Bill(product of seller) => setHistorySeller