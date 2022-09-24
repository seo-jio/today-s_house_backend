package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PostOrderReq {
    Long userIdx;
    Long productId;
    Long productOptionId;
    Integer productPrice;

    String buyerName;
    String email;
    String phoneNumber;
    String addressName;
    String receiverName;
    String receiverPhoneNumber;
    String address;
    Boolean selectAsDefault;
}
