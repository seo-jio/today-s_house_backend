package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PostOrderReq {
    Long buyerIdx;
    Long productId;
    Long productOptionId;
    Integer price;
    String paymentMethod;

    String buyerName;
    String email;
    String phoneNumber;

    String receiverName;
    String receiverPhoneNumber;

    String addressName;
    String postalCode;
    String address1;
    String address2;
    String request;
    Boolean selectAsDefault;
}
