package com.example.demo.src.seller.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class SimpleSeller {
    Long brandId;
    String brandName;
    String brandExplain;


    public void isValid() throws BaseException {
        if(brandName.length() > 45){
            throw new BaseException(BaseResponseStatus.POST_SELLER_BRAND_NAME_TOO_LONG);
        }
        if(brandExplain.length() > 150){
            throw new BaseException(BaseResponseStatus.POST_SELLER_BRAND_EXPLAIN_TOO_LONG);
        }
    }
}
