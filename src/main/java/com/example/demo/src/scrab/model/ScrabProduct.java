package com.example.demo.src.scrab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScrabProduct {
    private Long productId;
    private String thumbnailUrl;
    private String productName;
    private int originalPrice;
    private int discountedPrice;
    private int totalScore;
    private int numReviews;
    private String brandName;
}
