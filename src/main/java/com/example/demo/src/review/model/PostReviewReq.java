package com.example.demo.src.review.model;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PostReviewReq {
    Long userIdx;
    Long productId;
    Short score;
    String reviewPhotoUrl;
    String content;


    public void isValid() throws BaseException {
        if(content.length() > 150)
            throw new BaseException(BaseResponseStatus.POST_REVIEW_CONTENT_TOO_LONG);
    }
}
