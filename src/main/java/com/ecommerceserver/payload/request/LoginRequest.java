package com.ecommerceserver.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
  @NotBlank
  String email;
  @NotBlank
  String password;
}