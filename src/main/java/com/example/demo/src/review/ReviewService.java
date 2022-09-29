package com.example.demo.src.review;

import com.example.demo.src.review.model.PostReviewReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ReviewService {
    private final ReviewDao reviewDao;


    public Long createReview(PostReviewReq req) {
        return reviewDao.createReview(req.getProductId(), req.getUserIdx(), req.getContent(), req.getReviewPhotoUrl(), req.getScore());
    }

    public Boolean updateReview(Long reviewId, PostReviewReq req) {
        return reviewDao.updateReview(reviewId, req.getProductId(), req.getUserIdx(), req.getContent(), req.getReviewPhotoUrl(), req.getScore());
    }

    public Boolean deleteReview(Long reviewId) {
        return reviewDao.deleteReview(reviewId);
    }
}
