package com.example.demo.src.seller.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostSellerReq {
    String brandName;
    String brandExplain;

}
