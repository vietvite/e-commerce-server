package com.ecommerceserver.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
  
  @NotBlank(message = "Vui lòng nhập họ và tên")
  @Size(min = 5, max = 50, message = "Họ tên phải dài từ 5 đên 50 ký tự")
  private String fullname;
  
  @NotBlank(message = "Vui lòng nhập email")
  @Size(max = 50, message = "Email dài tối đa là 50 ký tự")
  @Email(message = "Email không hợp lệ")
  private String email;

  private String username;
  private String phoneNumber;

  String role;

  @NotBlank
  @Size(min = 6, message = "Mật khẩu phải lớn hơn 6 ký tự")
  private String password;
}