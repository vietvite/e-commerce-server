package com.ecommerceserver.controller;

import com.ecommerceserver.model.Product;
import com.ecommerceserver.payload.response.MessageResponse;
import com.ecommerceserver.security.services.UserDetailsImpl;
import com.ecommerceserver.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartService cartService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> addCart(@RequestBody Product product) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    int rs = cartService.setCart(userId, product);
    if (rs == -1) {
      return ResponseEntity.ok(new MessageResponse(false, "Sản phẩm đã có trong danh sách yêu thích."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(true, "Đã thêm vào danh sách yêu thích."));
    } else {
      return ResponseEntity.ok(new MessageResponse(false, "Thêm vào danh sách yêu thích không thành công."));
    }
  }

}