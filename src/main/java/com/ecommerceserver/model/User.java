package com.ecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
@JsonIgnoreProperties({"password"})
public class User {
  @Id
  String id;

  String email;
  String password;
  String username;
}