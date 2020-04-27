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
public class FavoriteServiceImpl implements FavoriteService {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  UserRepository userRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public List<Product> getAll(String userId) {
    Optional<Customer> customer = customerRepository.findById(userId);
    return customer.get().getListFavorite() != null ? List.copyOf(customer.get().getListFavorite()) : new ArrayList<>();
  }

  /**
   * Add product to favorite list
   * 
   * @return -1: product existed in list
   * @return 1: product added to list
   * @return 0: productId or userId not found
   */
  @Override
  public int addOne(String userId, String productId) {
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Optional<Product> productOpt = productRepository.findById(productId);

    Update update = new Update().addToSet("listFavorite", productOpt.get());
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

  /**
   * Remove product out of favorite list
   * 
   * @return -1: product existed in list
   * @return 1: product removed from list
   * @return 0: productId or userId not found or something unexpected happen
   */
  @Override
  public int removeOne(String userId, String productId) {
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Query productQuery = new Query(Criteria.where("_id").is(productId));
    Update update = new Update().pull("listFavorite", productQuery);
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