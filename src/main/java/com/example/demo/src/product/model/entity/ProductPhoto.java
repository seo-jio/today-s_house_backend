package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class ProductPhoto {
    Long productPhotoId;
    Long productId;
    String productPhotoUrl;
    Integer sequenceNo;
    Date createdAt;
    Date updatedAt;
    String status;
}
