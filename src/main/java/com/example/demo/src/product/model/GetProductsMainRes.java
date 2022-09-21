package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class GetProductsMainRes {
    List<ProductThumbnail> todaysDeal;
    List<ProductThumbnail> favorites;

}
