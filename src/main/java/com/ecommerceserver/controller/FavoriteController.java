package com.ecommerceserver.controller;

import java.util.List;

import com.ecommerceserver.model.Product;
import com.ecommerceserver.payload.response.MessageResponse;
import com.ecommerceserver.security.services.UserDetailsImpl;
import com.ecommerceserver.services.FavoriteService;

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
@RequestMapping("/favorite")
public class FavoriteController {

  @Autowired
  FavoriteService favoriteService;

  @GetMapping
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> getFavorite() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    List<Product> body = favoriteService.getAll(userId);
    return ResponseEntity.ok(body);
  }

  @PostMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> addCart(@PathVariable String productId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    int rs = favoriteService.addOne(userId, productId);
    if (rs == -1) {
      return ResponseEntity.ok(new MessageResponse(false, "Sản phẩm đã có trong danh sách yêu thích."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(true, "Đã thêm vào danh sách yêu thích."));
    } else {
      return ResponseEntity.ok(new MessageResponse(false, "Sản phẩm không tồn tại."));
    }
  }

  @DeleteMapping("/{productId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> removeCart(@PathVariable String productId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();

    int rs = favoriteService.removeOne(userId, productId);
    if (rs == -1) {
      return ResponseEntity
          .ok(new MessageResponse(false, "Xóa sản phẩm không thành công. Sản phẩm chưa có trong danh sách yêu thích."));
    } else if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(true, "Đã xóa sản phẩm khỏi danh sách yêu thích."));
    } else {
      return ResponseEntity
          .ok(new MessageResponse(false, "Xóa sản phẩm không thành công. Sản phẩm chưa có trong danh sách yêu thích."));
    }
  }
}