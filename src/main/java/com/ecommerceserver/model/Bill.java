package com.ecommerceserver.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

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
}

// Bill customer = new Bill(); => set history customer

// customer => listProduct => for

// sellter => new Bill(product of seller) => setHistorySeller