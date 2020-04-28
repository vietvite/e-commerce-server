package com.ecommerceserver.services;

import java.util.List;

import com.ecommerceserver.model.Bill;

public interface BillService {
  List<Bill> getAllCustomerBill(String userId);
  int addCustomerBill(String userId);

  int addSellerBill(String userId, Bill bill);
  List<Bill> getAllSellerBill(String sellerId);
  List<Bill> acceptBillSeller(String sellerId, String billId);
  List<Bill> denyBillSeller(String sellerId, String billId);
}