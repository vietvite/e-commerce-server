package com.ecommerceserver.configs;

import java.util.List;

import com.ecommerceserver.model.Role;
import com.ecommerceserver.respository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = RoleRepository.class)
@Configuration
public class MongoDBConfig {

  @Bean
  CommandLineRunner commandLineRunner(RoleRepository roleRepository) {
    return (args) -> {
      roleRepository.saveAll(
        List.of(
          new Role("1", "ROLE_CUSTOMER"),
          new Role("2", "ROLE_SELLER"),
          new Role("3", "ROLE_ADMIN")
      ));
    };
  }

}