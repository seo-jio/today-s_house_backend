package com.example.demo.src.product.model;

import com.example.demo.src.category.model.Category;
import com.example.demo.src.product.model.entity.ProductOption;
import com.example.demo.src.review.model.ReviewDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class GetProductDetailRes {
    Long category2;
    List<Category> categoryList;
    List<String> productPhotos;
    Long productId;
    String productName;
    Float totalScore;
    Integer numReviews;
    Integer price;
    Integer discountedPrice;
    String brandName;
    List<String> expPhotos;
    // TODO : Review Thumbnail 만든 이후에는 object -> reviewThumbnail class로 바꾸기.
    List<ReviewDetail> reviews;

    List<ProductOption> options;
}
