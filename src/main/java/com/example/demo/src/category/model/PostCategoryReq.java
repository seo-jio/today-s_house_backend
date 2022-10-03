package com.example.demo.src.category.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import lombok.Data;

@Data
public class PostCategoryReq {
    Long parentCategoryId;
    String categoryName;

    public void isValid() throws BaseException {
        if(categoryName.length() == 0 || categoryName.length() > 45){
            throw new BaseException(BaseResponseStatus.POST_CATEGORY_NAME_TOO_LONG);
        }
    }
}
