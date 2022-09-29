package com.example.demo.src.product.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor
public class ProductThumbnail{
    Long productId;
    String thumbnailUrl;
    String name;
    Integer originalPrice;
    Integer discountedPrice;
    Boolean isScrabbed;
    Float totalScore;
    Integer numReviews;
    String brandName;
    Boolean isTodaysDeal;
    LocalDateTime eventDeadline;
}
