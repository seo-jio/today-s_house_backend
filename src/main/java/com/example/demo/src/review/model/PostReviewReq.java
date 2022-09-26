package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PostReviewReq {
    Long userIdx;
    Long productId;
    Short score;
    String reviewPhotoUrl;
    String content;

}
