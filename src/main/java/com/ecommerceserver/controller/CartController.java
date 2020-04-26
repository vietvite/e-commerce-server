package com.ecommerceserver.controller;

import java.util.List;

import com.ecommerceserver.model.Product;
import com.ecommerceserver.payload.response.MessageResponse;
import com.ecommerceserver.respository.UserRepository;
import com.ecommerceserver.security.services.UserDetailsImpl;
import com.ecommerceserver.services.CartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

  @Autowired
  CartService cartService;

  @PostMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> addCart(@PathVariable String productId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    int rs = cartService.addOne(userId, productId);
    if (rs == -1) {
      return ResponseEntity.ok(new MessageResponse(false, "Sản phẩm đã có trong giỏ hàng."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(true, "Đã thêm vào giỏ hàng."));
    } else {
      return ResponseEntity
          .ok(new MessageResponse(false, "Thêm vào giỏ hàng không thành công. Sản phẩm không tồn tại."));
    }
  }

  @GetMapping
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> getCart() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    List<Product> body = cartService.getAll(userId);
    return ResponseEntity.ok(body);
  }

  @DeleteMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> removeCart(@PathVariable String productId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();

    int rs = cartService.removeOne(userId, productId);
    if (rs == -1) {
      return ResponseEntity
          .ok(new MessageResponse(false, "Xóa sản phẩm không thành công. Sản phẩm chưa có trong giỏ hàng."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(true, "Đã xóa sản phẩm khỏi giỏ hàng."));
    } else {
      return ResponseEntity
          .ok(new MessageResponse(false, "Xóa sản phẩm không thành công. Sản phẩm chưa có trong giỏ hàng."));
    }
  }

  @PutMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> updateQuantity(@PathVariable String productId,
      @RequestParam(defaultValue = "1") int quantity) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();

    int rs = cartService.updateQuantity(userId, productId, quantity);
    if (rs == -1) {
      return ResponseEntity.ok(new MessageResponse(false, "Error: Cập nhật số lượng không thành công."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(true, "Cập nhật số lượng thành công."));
    } else {
      return ResponseEntity
          .ok(new MessageResponse(false, "Cập nhật số lượng không thành công. Sản phẩm chưa có trong giỏ hàng."));
    }
  }

}