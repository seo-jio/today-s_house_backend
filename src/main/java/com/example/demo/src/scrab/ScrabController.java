package com.example.demo.src.scrab;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.scrab.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<ScrabItemWrapper> getScrabItems(@PathVariable Long userIdx){
        try {
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != userIdx){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<ScrabItem> joinedScrabItems = scrabService.getScrabItems(userIdx);
            ScrabBanner scrabBanner = scrabService.getScrabBanner(userIdx);
            ScrabItemWrapper scrabItemWrapper = new ScrabItemWrapper(scrabBanner, joinedScrabItems);
            return new BaseResponse<>(scrabItemWrapper);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{userIdx}/scrabs/photo")
    public BaseResponse<ScrabItemWrapper> getScrabPhoto(@PathVariable Long userIdx){
        try {
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != userIdx){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<ScrabItem> scrabItems = scrabService.getScrabPhotos(userIdx);
            ScrabBanner scrabBanner = scrabService.getScrabBanner(userIdx);
            ScrabItemWrapper scrabItemWrapper = new ScrabItemWrapper(scrabBanner, scrabItems);
            return new BaseResponse<>(scrabItemWrapper);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @ResponseBody
    @GetMapping("/{userIdx}/scrabs/product")
    public BaseResponse<ScrabProductWrapper> getScrabProductTab(@PathVariable Long userIdx){
        try {
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != userIdx){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<ScrabProduct> scrabProducts = scrabService.getScrabProductTab(userIdx);
            ScrabBanner scrabBanner = scrabService.getScrabBanner(userIdx);
            ScrabProductWrapper scrabProductWrapper = new ScrabProductWrapper(scrabBanner, scrabProducts);
            return new BaseResponse<>(scrabProductWrapper);
        } catch (BaseException exception) {
            return new BaseResponse<>((exception.getStatus()));
        }
    }


    @ResponseBody
    @GetMapping("/{userIdx}/scrabs/productCategory")
    public BaseResponse<ScrabProductWrapper> getScrabProductTabFilter(@PathVariable Long userIdx, @RequestParam Long categoryId){
        try {
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != userIdx){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            List<ScrabProduct> scrabProducts = scrabService.getScrabProductTabFilter(userIdx, categoryId);
            ScrabBanner scrabBanner = scrabService.getScrabBanner(userIdx);
            ScrabProductWrapper scrabProductWrapper = new ScrabProductWrapper(scrabBanner, scrabProducts);
            return new BaseResponse<>(scrabProductWrapper);
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
            if(scrabService.validateScrabProduct(userIdx, productId) == false){
                return new BaseResponse<>(ALREADY_SCRABBED);
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
            if(scrabService.validateScrabPhoto(userIdx, photoId) == false){
                return new BaseResponse<>(ALREADY_SCRABBED);
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
