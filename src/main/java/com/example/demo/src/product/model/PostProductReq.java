package com.example.demo.src.product.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Data
public class PostProductReq {
    String productName;
    Long sellerId;
    Integer originalPrice;
    Integer discountedPrice;
    Long category1;
    Long category2;
    String isTodayDeal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date eventDeadline;
    List<String> productPhotos;
    List<String> expPhotos;
    List<String> optionNames;
    List<Integer> optionPrices;

    public boolean isInValid() {
        return productName == null || sellerId == null || originalPrice == null || category1 == null || category2 == null
                || isTodayDeal == null || optionNames == null || optionPrices == null || optionNames.size() == 0 || productPhotos == null ||
                expPhotos == null;
    }
}
