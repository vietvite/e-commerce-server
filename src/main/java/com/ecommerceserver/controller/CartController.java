package com.ecommerceserver.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('ROLE_CUSTOMER')")
  public String test(@PathVariable String id) {
    
    return "Yo " + id + ", you passed men.";
  }
}