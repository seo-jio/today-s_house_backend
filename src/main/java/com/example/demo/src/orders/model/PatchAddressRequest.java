package com.example.demo.src.orders.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class PatchAddressRequest {
    String addressName;
    String postalCode;
    String address1;
    String address2;
    String receiverName;
    String receiverPhoneNumber;
}
