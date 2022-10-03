package com.example.demo.src.orders.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
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

    public void isValid() throws BaseException {
        if(receiverName.length() > 45)
            throw new BaseException(BaseResponseStatus.PATCH_ORDER_RECEIVER_NAME_TOO_LONG);
        if(postalCode.length() != 5)
            throw new BaseException(BaseResponseStatus.PATCH_ORDER_POSTAL_CODE_LENGTH_WRONG);
        if(address1.length() > 150)
            throw new BaseException(BaseResponseStatus.PATCH_ORDER_ADDRESS1_TOO_LONG);
        if(address1.length() > 150)
            throw new BaseException(BaseResponseStatus.PATCH_ORDER_ADDRESS2_TOO_LONG);

    }
}
