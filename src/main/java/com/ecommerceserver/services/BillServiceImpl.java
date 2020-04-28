package com.ecommerceserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Bill;
import com.ecommerceserver.model.Customer;
import com.ecommerceserver.respository.CustomerRepository;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Override
  public List<Bill> getAllCustomerBill(String userId) {
    Optional<Customer> customer = customerRepository.findById(userId);
    List<Bill> bills = customer.get().getOrderHistory();
    return bills != null ? bills : new ArrayList<>();
  }

  @Override
  public int addOne(String userId) {
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

}