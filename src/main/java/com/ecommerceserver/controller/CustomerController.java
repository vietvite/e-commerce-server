package com.ecommerceserver.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class CustomerController {

  @Autowired
  CustomerRepository customerRepository;
  
  @Autowired
  BillService billService;


  @PostMapping("/account/address")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> updateAddress(@RequestBody Address address) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    Customer customer = customerRepository.findById(userId).get();
    customer.setAddress(address);
    Customer updatedCustomer = customerRepository.save(customer);
    
    return ResponseEntity.ok(updatedCustomer.getAddress());
  }

  @GetMapping("/account/address")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> getAddress() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    Customer customer = customerRepository.findById(userId).get();
    
    return ResponseEntity.ok(customer.getAddress());
  }

  @GetMapping("/account")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> getDetail() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    Customer customer = customerRepository.findById(userId).get();
    
    return ResponseEntity.ok(customer);
  }

  @PostMapping("/account")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> updateAccount(@RequestBody Customer reqBodyCustomer) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();
    Customer customer = customerRepository.findById(userId).get();

    if(reqBodyCustomer.getAddress() != null) {
      customer.setAddress(reqBodyCustomer.getAddress());
    }
    if(reqBodyCustomer.getFullname() != null) {
      customer.setFullname(reqBodyCustomer.getFullname());
    }
    if(reqBodyCustomer.getPhoneNumber() != null) {
      customer.setPhoneNumber(reqBodyCustomer.getPhoneNumber());
    }

    Customer updatedCustomer = customerRepository.save(customer);
    
    return ResponseEntity.ok(updatedCustomer);
  }

  @PostMapping("/bill")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public ResponseEntity<?> addBill() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userId = ((UserDetailsImpl) principal).getId();

    int rs = billService.addCustomerBill(userId);
    if (rs == 1) {
      return ResponseEntity.ok(new MessageResponse(rs, "Đặt hàng thành công."));
    } else {
      return ResponseEntity
          .ok(new MessageResponse(rs, "Đặt hàng không thành công. Sản phẩm chưa có trong giỏ hàng."));
    }
  }
}