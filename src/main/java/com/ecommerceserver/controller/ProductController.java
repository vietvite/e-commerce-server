package com.ecommerceserver.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Category;
import com.ecommerceserver.model.Product;
import com.ecommerceserver.model.ReviewStar;
import com.ecommerceserver.services.CategoryService;
import com.ecommerceserver.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable String id) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      return ResponseEntity.ok(product);
    }
    return ResponseEntity.noContent().build();
  }

  @PostMapping
  public List<Product> addProduct(@RequestBody List<Product> lstProduct) {
    for (Product product : lstProduct) {
      ReviewStar reviewStar = new ReviewStar();
      product.setReviewStar(reviewStar);
    }
    return productService.addList(lstProduct);
  }

  @GetMapping("/home")
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

  @GetMapping("")
  public List<Product> getProductByCategory(@RequestParam(defaultValue = "1") Integer page,
      @RequestParam(defaultValue = "title") String sortBy,
      @RequestParam(defaultValue = "ascending") String sortDirection, @RequestParam(defaultValue = "") String title,
      @RequestParam(defaultValue = "") String categoryId, @RequestParam Long priceFrom, @RequestParam Long priceTo,
      @RequestParam Integer reviewStar, @RequestParam(defaultValue = "") String search) {
    String asc = "ascending";
    Sort sort;
    List<Product> temp;
    page--;
    // set sort
    if (sortDirection.equals(asc)) {
      sort = Sort.by(Sort.Direction.ASC, sortBy);
    } else {
      sort = Sort.by(Sort.Direction.DESC, sortBy);
    }
    temp = productService.getProduct(sort);
    // apply filter
    List<Product> list = new ArrayList<Product>();
    if (title.isEmpty()) {
      temp.removeIf(product -> product.getPrice() < priceFrom || product.getPrice() > priceTo
          || product.getAvarageStar() < reviewStar);
    } else {
      temp.removeIf(product -> product.getPrice() < priceFrom || product.getPrice() > priceTo
          || product.getAvarageStar() < reviewStar || !product.getTitle().contains(title));
    }
    for (int i = page * 15, n = temp.size(); i < n && list.size() < 15; i++) {
      list.add(temp.get(i));
    }
    return list;
  }
}
