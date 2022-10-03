package com.example.demo.src.orders.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
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


    public void isValid() throws BaseException{
        String orderMethods = "KAKAO, CARD, BANK, PAYCO, TOSS ";
        if(!orderMethods.contains(paymentMethod))
            throw new BaseException(BaseResponseStatus.POST_ORDER_INVALID_PAYMENT_METHOD);
        if(buyerName.length() > 45)
            throw new BaseException(BaseResponseStatus.POST_ORDER_BUYER_NAME_TOO_LONG);
        if(receiverName.length() > 45)
            throw new BaseException(BaseResponseStatus.POST_ORDER_RECEIVER_NAME_TOO_LONG);
        if(postalCode.length() != 5)
            throw new BaseException(BaseResponseStatus.POST_ORDER_POSTAL_CODE_LENGTH_WRONG);
        if(address1.length() > 150)
            throw new BaseException(BaseResponseStatus.POST_ORDER_ADDRESS1_TOO_LONG);
        if(address1.length() > 150)
            throw new BaseException(BaseResponseStatus.POST_ORDER_ADDRESS2_TOO_LONG);
        if(request.length() > 150)
            throw new BaseException(BaseResponseStatus.POST_ORDER_REQUEST_TOO_LONG);

    }
}
