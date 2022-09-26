package com.example.demo.src.review;

import com.example.demo.src.review.model.ReviewDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class ReviewProvider {
    private final ReviewDao reviewDao;

    public Boolean isReviewIdExist(Long reviewId) {
        return reviewDao.isReviewIdExist(reviewId);
    }

    public ReviewDetail findByReviewId(Long reviewId) {
        return reviewDao.findByReviewId(reviewId);
    }

    public List<ReviewDetail> findByWriterIdx(Long userIdxByJwt) {
        return reviewDao.findByWriterIdx(userIdxByJwt);
    }

    public List<ReviewDetail> findByProductId(Long productId) {
        return reviewDao.findByProductId(productId);
    }
}
