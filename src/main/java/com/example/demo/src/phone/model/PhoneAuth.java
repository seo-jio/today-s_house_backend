package com.example.demo.src.phone.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PhoneAuth {
    Long phoneAuthId;
    String phoneNumber;
    String code;
    String isAuth;
}
