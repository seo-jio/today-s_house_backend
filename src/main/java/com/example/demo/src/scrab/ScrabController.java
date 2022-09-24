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

import static com.example.demo.config.BaseResponseStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class ScrabController {
    private final JwtService jwtService;
    private final ScrabService scrabService;

    @ResponseBody
    @GetMapping("/{userIdx}/scrabs")
    public BaseResponse<List<ScrabItem>> getScrabItems(@PathVariable Long userIdx, @RequestParam() String filter){
        try {
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != userIdx){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            // 문자열 비교 시 '=='을 사용하면 주소 값을 비교하기 떄문에 .equlas()를 사용하자!!
            if (filter.equals("All")){  //모두 보기
                List<ScrabItem> joinedScrabItems = scrabService.getScrabItems(userIdx);
                return new BaseResponse<>(joinedScrabItems);
            }
            else if(filter.equals("Product")){ //상품 보기
                List<ScrabItem> scrabItems = scrabService.getScrabProducts(userIdx);
                return new BaseResponse<>(scrabItems);
            }
            else if(filter.equals("Photo")){  //사진 보기
                List<ScrabItem> scrabItems = scrabService.getScrabPhotos(userIdx);
                return new BaseResponse<>(scrabItems);
            }
            else{
                return new BaseResponse<>(SCRAB_TYPE_INVALID);
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
