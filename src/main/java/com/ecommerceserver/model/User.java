package com.ecommerceserver.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class User {
  @Id
  String id;

  String email;
  String password;
  String username;
}