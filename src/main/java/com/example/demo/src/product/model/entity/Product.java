package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class Product {
    Long productId;
    String productName;
    Long sellerId;
    Integer originalPrice;
    Integer discountedPrice;
    Long category1;
    Long category2;
    Boolean isTodayDeal;
    Date createdAt;
    Date updatedAt;
    String status;
}
