package com.ecommerceserver.controller;

import java.util.List;

import com.ecommerceserver.model.Product;
import com.ecommerceserver.payload.response.MessageResponse;
import com.ecommerceserver.security.services.UserDetailsImpl;
import com.ecommerceserver.services.OrderLaterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/orderlater")
public class OrderLaterController {

  @Autowired
  OrderLaterService orderLaterService;

  /**
   * Get all product in order later
   */
  @GetMapping
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> getOrderLater() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    List<Product> body = orderLaterService.getAll(userId);
    return ResponseEntity.ok(body);
  }

  /**
   * Add product to order later list and remove the one in cart
   */
  @PostMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> addOrderLater(@PathVariable String productId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    int rs = orderLaterService.moveFromCartToOrderLater(userId, productId);
    if (rs == -1) {
      return ResponseEntity.ok(new MessageResponse(rs, "Sản phẩm đã có trong danh sách mua sau."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(rs, "Đã thêm vào danh sách mua sau."));
    } else {
      return ResponseEntity
          .ok(new MessageResponse(rs, "Thêm vào danh sách mua sau không thành công. Sản phẩm không tồn tại."));
    }
  }

  @PostMapping("/backtocart/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> addBackToCart(@PathVariable String productId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();

    int rs = orderLaterService.addBackToCart(userId, productId);
    if (rs == -1) {
      return ResponseEntity.ok(new MessageResponse(rs, "Thêm sản phẩm vào giỏ hàng thành công."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(rs, "Thêm sản phẩm vào giỏ hàng thành công."));
    } else {
      return ResponseEntity.ok(new MessageResponse(rs, "Sản phẩm không tìm thấy."));
    }
  }

  @DeleteMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> removeCart(@PathVariable String productId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();

    int rs = orderLaterService.removeOne(userId, productId);
    if (rs == -1) {
      return ResponseEntity
          .ok(new MessageResponse(rs, "Xóa sản phẩm không thành công. Sản phẩm chưa có trong danh sách mua sau."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(rs, "Đã xóa sản phẩm khỏi danh sách mua sau."));
    } else {
      return ResponseEntity.ok(new MessageResponse(rs, "Xóa sản phẩm không thành công. Sản phẩm không tồn tại."));
    }
  }
}