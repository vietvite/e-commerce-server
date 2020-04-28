package com.ecommerceserver.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.ecommerceserver.model.Category;
import com.ecommerceserver.model.Product;
import com.ecommerceserver.model.ReviewStar;
import com.ecommerceserver.model.Seller;
import com.ecommerceserver.model.User;
import com.ecommerceserver.respository.UserRepository;
import com.ecommerceserver.security.services.UserDetailsImpl;
import com.ecommerceserver.services.CategoryService;
import com.ecommerceserver.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  CategoryService categoryService;

  @Autowired
  UserRepository userRepository;

  @GetMapping("/{id}")
  public ResponseEntity<?> getProductById(@PathVariable String id) {
    Optional<Product> product = productService.findById(id);
    if (product.isPresent()) {
      return ResponseEntity.ok(product);
    }
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/shop")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public List<Product> getProductOfShop() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sellerId = ((UserDetailsImpl) principal).getId();
    List<Product> listProduct = productService.findAll();
    listProduct.removeIf(p -> (!p.getSeller().getId().equals(sellerId)));
    return listProduct;
  }

  @PostMapping
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public List<Product> addProduct(@RequestBody Product product) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sellerId = ((UserDetailsImpl) principal).getId();
    User seller = userRepository.findById(sellerId).get();

    ReviewStar reviewStar = new ReviewStar();
    product.setSeller(seller);
    product.setReviewStar(reviewStar);

    productService.addProduct(product);
    return productService.findBySellerId(sellerId);
  }

  @PostMapping("/edit")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public List<Product> editProduct(@RequestBody Product product) {
    Product temp = productService.editProduct(product);
    return productService.findBySellerId(product.getSeller().getId());
  }

  @PostMapping("/delete/{productId}")
  @PreAuthorize("hasRole('ROLE_SELLER')")
  public List<Product> deleteProduct(@PathVariable String productId) {
    Product temp = productService.deleteById(productId);
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String sellerId = ((UserDetailsImpl) principal).getId();
    return productService.findBySellerId(sellerId);
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
          || product.getAvarageStar() < reviewStar || !product.getCategory().getId().equals(categoryId));
    } else {
      temp.removeIf(product -> product.getPrice() < priceFrom || product.getPrice() > priceTo
          || product.getAvarageStar() < reviewStar
          || !Pattern.compile(Pattern.quote(title), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE)
              .matcher(product.getTitle()).find());
    }
    for (int i = page * 15, n = temp.size(); i < n && list.size() < 15; i++) {
      list.add(temp.get(i));
    }
    return list;
  }

  @RequestMapping(value = "/image", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<Object> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
    Date date = new Date();
    String[] namePart = file.getOriginalFilename().split("\\.");
    String fileName = Long.toString(date.getTime()) + "." + namePart[1];
    String path = "D:\\" + fileName;
    File convertFile = new File(path);
    convertFile.createNewFile();
    FileOutputStream fout = null;
    try {
      fout = new FileOutputStream(convertFile);
      fout.write(file.getBytes());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } finally {
      fout.close();
    }
    return new ResponseEntity<>(fileName, HttpStatus.OK);
  }
}
