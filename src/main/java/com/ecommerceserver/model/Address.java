package com.ecommerceserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
  String provinceOrCity = "";
  String districtOrTown = "";
  String subDistrictOrVillage = "";
  String detailAddress = "";
}