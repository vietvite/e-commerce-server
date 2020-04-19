package com.ecommerceserver.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ecommerceserver.model.Product;
import com.ecommerceserver.respository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	private final MongoTemplate mongoTemplate;
	@Autowired
	public ProductServiceImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	} 
	
  @Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> getList(Integer page, Integer size, String sortBy) {
		Pageable paging = PageRequest.of(page - 1, size, Sort.by(sortBy));
    Page<Product> pagedResult = productRepository.findAll(paging);
    if(pagedResult.hasContent()) {
      return pagedResult.getContent();
    }
    return new ArrayList<>();
	}

	@Override
	public Optional<Product> findById(String productId) {
    return productRepository.findById(productId);
	}

	@Override
	public List<Product> addList(List<Product> lstProduct) {
		return productRepository.saveAll(lstProduct);
	}

	@Override
	public List<Product> searchByTitle(String title) {
		Criteria regex = Criteria.where("title").regex(".*"+title+".*", "i");
		return mongoTemplate.find(new Query().addCriteria(regex), Product.class);
	}
}