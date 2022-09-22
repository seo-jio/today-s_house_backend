package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class ProductOption {
    Long productOptionId;
    String optionName;
    Integer optionPrice;
    Long productId;
    Date createdAt;
    Date updatedAt;
    String status;
}
