package com.ecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Document
// @AllArgsConstructor
// @NoArgsConstructor
// @RequiredArgsConstructor
@JsonIgnoreProperties({ "password" })
public class User {
  @Id
  String id;

  String email;
  String password;
  String username;
  Role role;

  public User(String email, String password, String username, Role role) {
    this.email = email;
    this.password = password;
    this.username = username;
    this.role = role;
  }

  public User() {
  }

  public User(String username, String email, String password) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

}