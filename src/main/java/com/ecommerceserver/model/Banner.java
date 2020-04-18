package com.ecommerceserver.model;

import org.springframework.data.annotation.Id;

public class Banner {
  @Id
  String id;
  String img;
  String url;

  public Banner(String img, String url) {
    this.img = img;
    this.url = url;
  }

  public Banner() {
  }

  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}