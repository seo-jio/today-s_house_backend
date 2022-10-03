package com.example.demo.src.product.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
@Data
public class PostProductReq {
    String productName;
    Long sellerId;
    Integer originalPrice;
    Integer discountedPrice;
    Long category1;
    Long category2;
    String isTodayDeal;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date eventDeadline;
    List<String> productPhotos;
    List<String> expPhotos;
    List<String> optionNames;
    List<Integer> optionPrices;

    public void isInValid() throws BaseException {
        if(productName.length() > 150)
            throw new BaseException(BaseResponseStatus.POST_PRODUCT_NAME_TOO_LONG);
        if(eventDeadline.before(new Date()))
            throw new BaseException(BaseResponseStatus.POST_PRODUCT_DATE_TOO_LATE);
        if(expPhotos.size() == 0 || productPhotos.size() == 0 || optionNames.size() == 0 || optionPrices.size() == 0)
            throw new BaseException(BaseResponseStatus.POST_PRODUCT_REQUIRED_EMPTY);
        if(optionNames.size() != optionPrices.size())
            throw new BaseException(BaseResponseStatus.POST_PRODUCT_OPTION_NOT_MATCH);
    }
}
