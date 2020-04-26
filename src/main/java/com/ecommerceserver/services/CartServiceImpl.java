package com.ecommerceserver.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.CartProduct;
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
public class CartServiceImpl implements CartService {

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
    return customer.get().getListCart() != null ? List.copyOf(customer.get().getListCart()) : new ArrayList<>();
  }

  /**
   * Add product to cart list of user
   * 
   * @return -1: product existed in cart
   * @return 1: product added to cart
   * @return 0: productId or userId not found or something unexpected happen
   */
  @Override
  public int addOne(String userId, String productId) {
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Optional<Product> productOpt = productRepository.findById(productId);
    Product product = productOpt.get();
    CartProduct cartProduct = new CartProduct(product.getId(), product.getTitle(), product.getPrice(),
        product.getDescription(), product.getStock(), product.getImageUrl(), product.getCategory(), product.getSeller(),
        product.getReviewStar(), product.getCreateAt(), product.getUpdateAt(), 1);

    Update update = new Update().addToSet("listCart", cartProduct);
    UpdateResult result = mongoTemplate.updateFirst(userQuery, update, Customer.class);

    if (result != null) {
      long modified = result.getModifiedCount();
      long match = result.getMatchedCount();
      if (match != 0 && modified == 0) {
        // Product existed. Call increase quantity method
        // return this.updateQuantity(userId, productId);
        return -1;
      } else if (match != 0 && modified != 0) {
        return 1;
      }
    }
    return 0;
  }

  /**
   * Remove product in cart list of user
   * 
   * @return -1: product existed in cart
   * @return 1: product removed from cart
   * @return 0: productId or userId not found or something unexpected happen
   */
  @Override
  public int removeOne(String userId, String productId) {
    Query userQuery = new Query(Criteria.where("_id").is(userId));
    Query productQuery = new Query(Criteria.where("_id").is(productId));
    Update update = new Update().pull("listCart", productQuery);
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
   * Update product in cart list of user
   * 
   * @return -1: product existed in cart
   * @return 1: product removed from cart
   * @return 0: productId or userId not found or something unexpected happen
   */
  @Override
  public int updateQuantity(String userId, String productId, int quantity) {
    Query userQuery = new Query(
        Criteria.where("_id").is(userId).and("listCart").elemMatch(Criteria.where("_id").is(productId)));
    Update update = new Update().set("listCart.$.quantity", quantity);
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