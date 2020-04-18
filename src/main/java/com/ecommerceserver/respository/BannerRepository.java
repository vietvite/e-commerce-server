package com.ecommerceserver.respository;

import com.ecommerceserver.model.Banner;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface BannerRepository extends MongoRepository<Banner, String> {

}