package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
public class OrderDetail {
    Long orderId;
    Long productId;
    Long productOptionId;
    Long buyerIdx;
    Integer price;
    Integer deliveryStatus;
    String paymentMethod;
    Long addressId;
    String request;
    LocalDateTime createdAt;

    String productName;
    String optionName;

    String buyerName;
    String email;
    String phoneNumber;

    String addressName;
    String receiverName;
    String receiverPhoneNumber;
    String address1;
    String address2;
}
