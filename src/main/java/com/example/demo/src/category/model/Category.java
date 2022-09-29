package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter @AllArgsConstructor
public class Category {
    Long categoryId;
    Long parentCategoryId;
    String categoryName;
    Integer depth;
    String categoryPhoto;
    Date createdAt;
    Date updatedAt;
    String status;
}
