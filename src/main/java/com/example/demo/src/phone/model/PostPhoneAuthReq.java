package com.example.demo.src.phone.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PostPhoneAuthReq {
    String phoneNumber;
    String code;
    
}
