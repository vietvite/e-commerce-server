package com.ecommerceserver.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill {

  @Id
  String id;
  List<CartProduct> listProduct;
  Address deliveryAddress;
  Date orderDate;
  Date deliveryDate;

  
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
    this.orderDate = new Date();
    this.deliveryDate = new Date();
    this.status = -1;
  }
}

// Bill customer = new Bill(); => set history customer

// customer => listProduct => for

// sellter => new Bill(product of seller) => setHistorySeller