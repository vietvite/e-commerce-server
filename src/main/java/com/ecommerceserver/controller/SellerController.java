package com.ecommerceserver.controller;

import java.util.List;

import com.ecommerceserver.model.Address;
import com.ecommerceserver.model.Bill;
import com.ecommerceserver.model.Customer;
import com.ecommerceserver.payload.response.MessageResponse;
import com.ecommerceserver.respository.CustomerRepository;
import com.ecommerceserver.security.services.UserDetailsImpl;
import com.ecommerceserver.services.BillService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/shop")
public class SellerController {

  @Autowired
  CustomerRepository customerRepository;

  @Autowired
  BillService billService;


  @PostMapping("/pending/{sellerId}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> addBill(@PathVariable String sellerId, @RequestBody Bill bill) {
    int rs = billService.addSellerBill(sellerId, bill);
    if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(rs, "Đặt hàng thành công."));
    } else {
      return ResponseEntity
          .ok(new MessageResponse(rs, "Đặt hàng không thành công. Sản phẩm chưa có trong giỏ hàng."));
    }
  }

  @GetMapping("/pending")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<?> getAllBill() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sellerId = ((UserDetailsImpl) principal).getId();

    List<Bill> bill = billService.getAllSellerBill(sellerId);
    return ResponseEntity.ok(bill);
  }


  @GetMapping("/history")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<?> getAllPaidBill() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sellerId = ((UserDetailsImpl) principal).getId();

    List<Bill> bill = billService.getAllSellerPaidBill(sellerId);
    return ResponseEntity.ok(bill);
  }

  @PostMapping("/bill/{billId}")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<?> acceptBill(@PathVariable String billId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sellerId = ((UserDetailsImpl) principal).getId();

    List<Bill> bill = billService.acceptBillSeller(sellerId, billId);
    return ResponseEntity.ok(bill);
  }
  @DeleteMapping("/bill/{billId}")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public ResponseEntity<?> denyBill(@PathVariable String billId) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sellerId = ((UserDetailsImpl) principal).getId();

    List<Bill> bill = billService.denyBillSeller(sellerId, billId);
    return ResponseEntity.ok(bill);
  }



}
