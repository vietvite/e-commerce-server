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
@RequestMapping("/category")
public class CategoryController {

  @Autowired
  CategoryService categoryService;

  @GetMapping
  public List<Category> getAllCategories() {
    return categoryService.getList();
  }

  @PostMapping
  public List<Category> addListCategories(@RequestBody List<Category> lstCategories) {
    return categoryService.addList(lstCategories);
  }

}