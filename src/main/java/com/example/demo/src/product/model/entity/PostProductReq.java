package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Getter @Setter @AllArgsConstructor
public class PostProductReq {
    String productName;
    Long sellerId;
    Integer originalPrice;
    Integer discountedPrice;
    String category1;
    String category2;
    Boolean isTodayDeal;
    String eventDeadline;
    List<String> productPhotos;
    List<String> expPhotos;
    List<String> optionNames;
    List<String> optionPrices;
}
