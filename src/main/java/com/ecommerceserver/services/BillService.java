package com.ecommerceserver.services;

import java.util.List;

import com.ecommerceserver.model.Bill;

public interface BillService {
  List<Bill> getAllCustomerBill(String userId);
  int addOne(String userId);
}