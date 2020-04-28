package com.ecommerceserver.respository;

import java.util.Optional;

import com.ecommerceserver.model.Seller;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SellerRepository extends MongoRepository<Seller, String> {
  Optional<Seller> findByEmail(String email);
}