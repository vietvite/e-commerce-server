package com.ecommerceserver.respository;

import java.util.Optional;

import com.ecommerceserver.model.ERole;
import com.ecommerceserver.model.Role;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Integer> {
  Optional<Role> findByName(ERole name);
}