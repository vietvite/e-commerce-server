package com.ecommerceserver.services;

import java.util.List;

import com.ecommerceserver.model.Customer;
import com.ecommerceserver.model.Product;
import com.ecommerceserver.payload.response.MessageResponse;
import com.ecommerceserver.respository.UserRepository;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  UserRepository userRepository;

  @Override
  public List<Product> getCart(String userId) {
    // Query query = new Query(Criteria.where("id").is(userId));
    // query.fields().include("listCart");
    // // userRepository.findById(userId);
    // List<User> result = mongoTemplate.find(query, User.class);
    return null;
  }

  @Override
  public int setCart(String userId, Product product) {
    Query query = new Query(Criteria.where("_id").is(userId));
    Update update = new Update();
    update.addToSet("listCart", product);
    UpdateResult result = mongoTemplate.updateFirst(query, update, Customer.class);

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