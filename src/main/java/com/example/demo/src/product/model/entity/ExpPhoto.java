package com.example.demo.src.product.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor

public class ExpPhoto {
    Long expPhotoId;
    Long productId;
    String expPhotoUrl;
    Integer sequenceNo;
    Date createdAt;
    Date updatedAt;
    String status;
}
