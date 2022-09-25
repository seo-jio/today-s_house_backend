package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor @NoArgsConstructor
public class OrderThumbnail {
    Long orderId;
    LocalDateTime createdAt;
    Integer deliveryStatus;
    String productName;
    String productPhotoUrl;
}
