package com.example.demo.src.phone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PostPhoneAuthReq {
    String phoneNumber;
    String code;
}
