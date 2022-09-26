package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class ReviewDetail {
    Long reviewId;
    Long productId;
    Long writerIdx;
    String writerName;
    String productName;
    String productPhotoUrl;
    String content;
    String reviewPhotoUrl;
    Integer score;
    Integer durability;
    Integer design;
    Integer price;
    Integer delivery;
}
