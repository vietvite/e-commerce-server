package com.ecommerceserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
@JsonIgnoreProperties({ "password" })
public class User {
  @Id
  String id;

  String email;
  String password;
  String username;
  Role role;
  String fullname;
  String phoneNumber;

  public User(String email, String password, String fullname, String phoneNumber) {
    this.email = email;
    this.password = password;
    this.username = extractUsernameFromEmail(email);
    this.fullname = fullname;
    this.phoneNumber = phoneNumber;
  }

  public User() {
  }

  public User(String username, String email, String password) {
    this.email = email;
    this.password = password;
    this.username = username;
  }

  String extractUsernameFromEmail(String email) {
    return email.substring(0, email.indexOf('@'));
  }
}