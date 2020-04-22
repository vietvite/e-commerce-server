package com.ecommerceserver.payload.response;

public class JwtResponse {
  String id;
  String username;
  String email;
  String token;
  String type = "Bearer";
  String role;
  public JwtResponse(String id, String username, String email, String token, String role) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.token = token;
    this.role = role;
  }
  public JwtResponse() {
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public String getToken() {
    return token;
  }
  public void setToken(String token) {
    this.token = token;
  }
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public String getRole() {
    return role;
  }
  public void setRole(String role) {
    this.role = role;
  }
}