package com.example.demo.src.category.model;

import lombok.Data;

@Data
public class PostCategoryReq {
    Long parentCategoryId;
    String categoryName;
}
