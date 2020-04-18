package com.ecommerceserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Product;
import com.ecommerceserver.respository.ProductRepository;

import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepository productRepository;

	@Override
	public List<Product> getListProduct(Integer page, Integer size, String sortBy) {
		Pageable paging = PageRequest.of(page - 1, size, Sort.by(sortBy));
    Page<Product> pagedResult = productRepository.findAll(paging);
    if(pagedResult.hasContent()) {
      return pagedResult.getContent();
    }
    return new ArrayList<>();
	}

	@Override
	public Optional<Product> findProductById(String productId) {
    return productRepository.findById(productId);
	}

	@Override
	public List<Product> addListProduct(List<Product> lstProduct) {
		return productRepository.saveAll(lstProduct);
	}

}