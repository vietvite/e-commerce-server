package com.ecommerceserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Category;
import com.ecommerceserver.model.Product;
import com.ecommerceserver.services.CategoryService;
import com.ecommerceserver.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;

  @GetMapping("/product")
  public List<Product> getListProduct(@RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "2") Integer size, @RequestParam(defaultValue = "title") String sortBy,
      @RequestParam(defaultValue = "") String search) {
    if (!search.isEmpty()) {
      return productService.searchByTitle(search);
    }
    return productService.getList(page, size, sortBy);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<?> getProductById(@PathVariable String id) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      return ResponseEntity.ok(product);
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/product")
  public List<Product> addProduct(@RequestBody List<Product> lstProduct) {
    return productService.addList(lstProduct);
  }

  @GetMapping("/homeproduct")
  public List<Product> getHomeProduct() {
    List<Category> listCategory = categoryService.getList();
    List<Product> listHomeProduct = new ArrayList<>();
    for (Category iCategory : listCategory) {
      List<Product> temp = productService.getFirst6(iCategory.getId());
      for (Product iProduct : temp) {
        listHomeProduct.add(iProduct);
      }
    }
    return listHomeProduct;
  }
}
