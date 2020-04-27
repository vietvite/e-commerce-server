package com.ecommerceserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Customer;
import com.ecommerceserver.model.Product;
import com.ecommerceserver.respository.CustomerRepository;
import com.ecommerceserver.respository.ProductRepository;
import com.ecommerceserver.respository.UserRepository;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class OrderLaterServiceImpl implements OrderLaterService {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  CartService cartService;

  @Override
  public List<Product> getAll(String userId) {
    Optional<Customer> customer = customerRepository.findById(userId);
    return customer.get().getListOrderLater() != null ? List.copyOf(customer.get().getListOrderLater())
        : new ArrayList<>();
  }

  /**
   * Move product from cart list to order later list
   * 
   * @return -1: product existed in cart
   * @return 1: product added to cart
   * @return 0: productId or userId not found or something unexpected happen
   */
  @Override
  public int moveFromCartToOrderLater(String userId, String productId) {
    // Add product to order later list
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Optional<Product> productOpt = productRepository.findById(productId);
    Update update = new Update().addToSet("listOrderLater", productOpt.get());
    UpdateResult addResult = mongoTemplate.updateFirst(userQuery, update, Customer.class);

    if (addResult != null) {
      // Remove product in cart list
      cartService.removeOne(userId, productId);
      long modified = addResult.getModifiedCount();
      long match = addResult.getMatchedCount();
      if (match != 0 && modified == 0) {
        return -1;
      } else if (match != 0 && modified != 0) {
        return 1;
      }
    }

    return 0;
  }

  /**
   * Remove product in order later list and add the one to cart
   * 
   * @return -1: product existed in cart
   * @return 1: product removed from cart
   * @return 0: productId or userId not found or something unexpected happen
   */
  @Override
  public int addBackToCart(String userId, String productId) {
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Query productQuery = new Query(Criteria.where("_id").is(productId));
    Update update = new Update().pull("listOrderLater", productQuery);
    UpdateResult result = mongoTemplate.updateFirst(userQuery, update, Customer.class);

    if (result != null) {
      cartService.addOne(userId, productId);
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
  public int removeOne(String userId, String productId) {
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Query productQuery = new Query(Criteria.where("_id").is(productId));
    Update update = new Update().pull("listOrderLater", productQuery);
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