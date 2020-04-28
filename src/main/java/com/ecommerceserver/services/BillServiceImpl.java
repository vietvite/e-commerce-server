package com.ecommerceserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Bill;
import com.ecommerceserver.model.Customer;
import com.ecommerceserver.model.Seller;
import com.ecommerceserver.respository.CustomerRepository;
import com.ecommerceserver.respository.SellerRepository;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  SellerRepository sellerRepository;

  @Autowired
  MongoOperations mongoOperations;

  @Override
  public List<Bill> getAllCustomerBill(String userId) {
    Optional<Customer> customer = customerRepository.findById(userId);
    List<Bill> bills = customer.get().getOrderHistory();
    return bills != null ? bills : new ArrayList<>();
  }

  @Override
  public int addCustomerBill(String userId) {
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Customer customer = customerRepository.findById(userId).get();
    if(customer.getListCart() == null) {return -1;}

    Bill bill = new Bill(customer.getListCart(), customer.getAddress());
    
    Update update = new Update().addToSet("orderHistory", bill).set("listCart", new ArrayList<>());
    UpdateResult result = mongoTemplate.updateFirst(userQuery, update, Customer.class);

    if (result != null) {
      long modified = result.getModifiedCount();
      long match = result.getMatchedCount();
      if (match != 0 && modified == 0) {
        return -1;
      } else if (match != 0 && modified != 0) {
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int addSellerBill(String sellerId, Bill reqBill) {
    Query userQuery = new Query(Criteria.where("_id").is(sellerId));
    
    Bill bill = new Bill(reqBill.getListProduct(), reqBill.getDeliveryAddress());
    
    Update update = new Update().addToSet("listBill", bill);
    UpdateResult result = mongoTemplate.updateFirst(userQuery, update, Seller.class);

    if (result != null) {
      long modified = result.getModifiedCount();
      long match = result.getMatchedCount();
      if (match != 0 && modified == 0) {
        return -1;
      } else if (match != 0 && modified != 0) {
        return 1;
      }
    }
    return 0;
  }

  @Override
  public List<Bill> getAllSellerBill(String sellerId) {
    Optional<Seller> seller = sellerRepository.findById(sellerId);
    List<Bill> bills = seller.get().getListBill();

    List<Bill> pendingBill = new ArrayList<>();
    for (Bill bill : bills) {
      if(bill.getStatus() == 0) {
        pendingBill.add(bill);
      }
    }
    return pendingBill;
  }

  @Override
  public List<Bill> acceptBillSeller(String sellerId, String billId) {
    Query userQuery = new Query(
        Criteria.where("_id").is(sellerId).and("listBill").elemMatch(Criteria.where("_id").is(billId)));
    Update update = new Update().set("listBill.$.status", 1);
    UpdateResult result = mongoTemplate.updateFirst(userQuery, update, Seller.class);

    return this.getAllSellerBill(sellerId);
  }

  @Override
  public List<Bill> denyBillSeller(String sellerId, String billId) {
    Query userQuery = new Query(
        Criteria.where("_id").is(sellerId).and("listBill").elemMatch(Criteria.where("_id").is(billId)));
    Update update = new Update().set("listBill.$.status", -1);
    UpdateResult result = mongoTemplate.updateFirst(userQuery, update, Seller.class);

    return this.getAllSellerBill(sellerId);
  }
}