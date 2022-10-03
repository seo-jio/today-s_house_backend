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
        if(score>5 || score < 0)
            throw new BaseException(BaseResponseStatus.POST_REVIEW_SCORE_INVALID);
        if(reviewPhotoUrl.length() > 150)
            throw new BaseException(BaseResponseStatus.POST_REVIEW_IMAGE_URL_TOO_LONG);
        if(content.length() > 150)
            throw new BaseException(BaseResponseStatus.POST_REVIEW_CONTENT_TOO_LONG);
    }

}
