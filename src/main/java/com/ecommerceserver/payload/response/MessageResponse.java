package com.ecommerceserver.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {
  int code;
  String message;

  public MessageResponse(String message) {
    this.message = message;
  }
}