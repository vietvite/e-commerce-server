package com.ecommerceserver.respository;

import java.util.Optional;

import com.ecommerceserver.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByEmail(String email);
  Boolean existsByUsername(String username);
  Boolean existsByEmail(String email);
}