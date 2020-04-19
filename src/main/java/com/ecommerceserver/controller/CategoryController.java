package com.ecommerceserver.controller;

import java.util.List;

import com.ecommerceserver.model.Category;
import com.ecommerceserver.services.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/category")
public class CategoryController {
  
  @Autowired
  CategoryService categoryService;

  @GetMapping("/menu")
  public List<Category> getAllCategories() {
    return categoryService.getList();
  }

  @PostMapping("/menu")
  public List<Category> addListCategories(@RequestBody List<Category> lstCategories) {
    return categoryService.addList(lstCategories);
  }
  
  @PostMapping("/menu/{categoryId}")
  public List<Category> addChildren(@PathVariable String categoryId, @RequestBody Category category) {
    return categoryService.addChildren(categoryId, category);
  }
}