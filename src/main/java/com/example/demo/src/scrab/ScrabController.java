package com.example.demo.src.scrab;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.scrab.model.ScrabItem;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;
import static com.example.demo.config.BaseResponseStatus.SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class ScrabController {
    private final JwtService jwtService;
    private final ScrabService scrabService;

    @ResponseBody
    @GetMapping("/{userIdx}/scrabs")
    public BaseResponse<List<ScrabItem>> getScrabItems(@PathVariable Long userIdx, @RequestParam String filter){
        try {
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != userIdx){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            if (filter == "All"){  //모두 보기
                List<ScrabItem> scrabItemsProduct = scrabService.getScrabProducts(userIdx);
                List<ScrabItem> scrabItemsPhoto = scrabService.getScrabPhotos(userIdx);
                List<ScrabItem> joinedScrabItems = new ArrayList<>();
                joinedScrabItems.addAll(scrabItemsProduct);
                joinedScrabItems.addAll(scrabItemsPhoto);
                Collections.sort(joinedScrabItems);
                return new BaseResponse<>(joinedScrabItems);
            }
            else if(filter == "Product"){ //상품 보기
                List<ScrabItem> scrabItems = scrabService.getScrabProducts(userIdx);
                return new BaseResponse<>(scrabItems);
            }
            else{  //사진 보기
                List<ScrabItem> scrabItems = scrabService.getScrabPhotos(userIdx);
                return new BaseResponse<>(scrabItems);
            }
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @PostMapping("/{userIdx}/scrabs/product/{productId}")
    public BaseResponse<?> scrabProduct(@PathVariable Long userIdx, @PathVariable Long productId){
        try{
            Long userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            scrabService.scrabProduct(userIdx, productId);
            return new BaseResponse<>(SUCCESS);
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping("/{userIdx}/scrabs/cancel/product/{productId}")
    public BaseResponse<?> scrabCancelProduct(@PathVariable Long userIdx, @PathVariable Long productId){
        try{
            Long userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            scrabService.scrabCancelProduct(userIdx, productId);
            return new BaseResponse<>(SUCCESS);
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    @ResponseBody
    @PostMapping("/{userIdx}/scrabs/photo/{photoId}")
    public BaseResponse<?> scrabPhoto(@PathVariable Long userIdx, @PathVariable Long photoId){
        try{
            Long userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            scrabService.scrabPhoto(userIdx, photoId);
            return new BaseResponse<>(SUCCESS);
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping("/{userIdx}/scrabs/cancel/photo/{photoId}")
    public BaseResponse<?> scrabCancelPhoto(@PathVariable Long userIdx, @PathVariable Long photoId){
        try{
            Long userIdxByJwt = jwtService.getUserIdx();
            if(userIdx != userIdxByJwt){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            scrabService.scrabCancelPhoto(userIdx, photoId);
            return new BaseResponse<>(SUCCESS);
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }
}
