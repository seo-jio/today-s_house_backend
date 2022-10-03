package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.review.model.PostReviewReq;
import com.example.demo.src.review.model.ReviewDetail;
import com.example.demo.src.user.UserProvider;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController @RequestMapping("/api/reviews") @RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewProvider reviewProvider;
    private final JwtService jwtService;
    private final ProductProvider productProvider;
    private final UserProvider userProvider;

    @PostMapping("")
    BaseResponse<?> createReivews(@RequestBody PostReviewReq req){
        Long userIdxByJwt = null;
        try {
            req.isValid();
            userIdxByJwt = jwtService.getUserIdx();
            if (!req.getUserIdx().equals(userIdxByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if(!userProvider.isExist(req.getUserIdx()))
                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        if(req.getScore() >5 || req.getScore() < 0){
            return new BaseResponse<>(BaseResponseStatus.POST_REVIEW_SCORE_INVALID);
        }
        if(!productProvider.isProductExist(req.getProductId()))
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_NOT_FOUND);
        Long reviewId = reviewService.createReview(req);
        return new BaseResponse<>(reviewId);
    }

    @PutMapping("/{reviewId}")
    BaseResponse<?> updateReview(@PathVariable Long reviewId, @RequestBody PostReviewReq req){
        Long userIdxByJwt = null;
        try {
            req.isValid();
            userIdxByJwt = jwtService.getUserIdx();
            if (!req.getUserIdx().equals(userIdxByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if(!userProvider.isExist(req.getUserIdx()))
                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        if(req.getScore() >5 || req.getScore() < 0){
            return new BaseResponse<>(BaseResponseStatus.POST_REVIEW_SCORE_INVALID);
        }
        if(!productProvider.isProductExist(req.getProductId()))
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_NOT_FOUND);
        if(!reviewProvider.isReviewIdExist(reviewId))
            return new BaseResponse<>(REVIEW_NOT_FOUND);
        Boolean success = reviewService.updateReview(reviewId, req);
        return new BaseResponse<>(reviewId);
    }


    @GetMapping("/{reviewId}")
    BaseResponse<?> getReviewDetail(@PathVariable Long reviewId){
        Long userIdxByJwt = null;
        try {
            userIdxByJwt = jwtService.getUserIdx();
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        if(!reviewProvider.isReviewIdExist(reviewId))
            return new BaseResponse<>(BaseResponseStatus.REVIEW_NOT_FOUND);
        ReviewDetail reviewDetail = reviewProvider.findByReviewId(reviewId);
        if (!reviewDetail.getWriterIdx().equals(userIdxByJwt)) {
            return new BaseResponse<>(INVALID_USER_JWT);
        }
        return new BaseResponse<>(reviewDetail);
    }

    @GetMapping("")
    BaseResponse<?> getReviewsByWriterIdx() {
        Long userIdxByJwt = null;
        try {
            userIdxByJwt = jwtService.getUserIdx();
            if (!userProvider.isExist(userIdxByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
        } catch (BaseException e) {
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(reviewProvider.findByWriterIdx(userIdxByJwt));
    }

    @GetMapping("/product/{productId}")
    BaseResponse<?> getReviewsByProductId(@PathVariable Long productId) {
        if(!productProvider.isProductExist(productId))
            return new BaseResponse<>(PRODUCT_NOT_FOUND);
        return new BaseResponse<>(reviewProvider.findByProductId(productId));
    }

    @DeleteMapping("/{reviewId}")
    BaseResponse<?> deleteReview(@PathVariable Long reviewId){
        Long userIdxByJwt = null;
        try {
            userIdxByJwt = jwtService.getUserIdx();
            if(!userProvider.isExist(userIdxByJwt))
                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        if(!reviewProvider.isReviewIdExist(reviewId))
            return new BaseResponse<>(REVIEW_NOT_FOUND);
        if(!reviewService.deleteReview(reviewId))
            return new BaseResponse<>(DATABASE_ERROR);
        return new BaseResponse<>(SUCCESS);
    }
}
