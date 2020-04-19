package com.ecommerceserver.services;

import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Category;

public interface CategoryService {
  List<Category> addList(List<Category> lCategories);
  List<Category> getList();
  Category addOne(Category category);
  Optional<Category> getById(String categoryId);
  List<Category> addChildren(String categoryId, Category category);
}