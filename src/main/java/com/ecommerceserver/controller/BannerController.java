package com.ecommerceserver.controller;

import java.util.List;

import com.ecommerceserver.model.Banner;
import com.ecommerceserver.respository.BannerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class BannerController {

  @Autowired
  BannerRepository bannerRepository;

  @RequestMapping(value = "/banner", method = RequestMethod.GET)
  public List<Banner> getBanners() {
    return bannerRepository.findAll();
  }

  @RequestMapping(value = "/admin/banner", method = RequestMethod.POST)
  public void addBanners(@RequestBody List<Banner> lstBanner) {
    // TODO: Upload banner image
    bannerRepository.saveAll(lstBanner);
  }

}