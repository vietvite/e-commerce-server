package com.ecommerceserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
  String id;
  String username;
  String fullname;
  String email;
  String token;
  String role;
}