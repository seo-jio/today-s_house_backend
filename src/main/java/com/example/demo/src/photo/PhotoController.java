package com.example.demo.src.photo;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.photo.model.*;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;
    private final JwtService jwtService;

    @ResponseBody
    @PostMapping()
    public BaseResponse<PostPhotoRes> createPhoto(@RequestBody PostPhotoReq postPhotoReq){
        try{
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != postPhotoReq.getUserIdx()){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            PostPhotoRes postPhotoRes = photoService.createPhoto(postPhotoReq);
            return new BaseResponse<>(postPhotoRes);
        }catch(BaseException baseException){
            System.out.println("baseException.getMessage() = " + baseException.getMessage());
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    @ResponseBody
    @GetMapping("/{photoId}")
    public BaseResponse<GetPhotoDetailRes> getPhotoDetail(@PathVariable Long photoId){
        try{
            GetPhotoDetailRes getPhotoDetailRes = photoService.getPhotoDetail(photoId);
            return new BaseResponse<>(getPhotoDetailRes);
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping("/delete/{userIdx}/{photoId}")
    public BaseResponse<?> deletePhoto(@PathVariable Long userIdx, @PathVariable Long photoId){
        try{
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != userIdx){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            photoService.deletePhoto(photoId);
            return null;
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping("/change-type")
    public BaseResponse<?> changeType(@RequestBody PatchPhotoTypeReq patchPhotoTypeReq){
        try{
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != patchPhotoTypeReq.getUserIdx()){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            photoService.changeType(patchPhotoTypeReq);
            return null;
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }

    @ResponseBody
    @PatchMapping("/change-text")
    public BaseResponse<?> changeText(@RequestBody PatchPhotoTextReq patchPhotoTextReq){
        try{
            Long userIdxFindByJwt = jwtService.getUserIdx();
            if(userIdxFindByJwt != patchPhotoTextReq.getUserIdx()){
                return new BaseResponse<>(INVALID_USER_JWT);
            }
            photoService.changeText(patchPhotoTextReq);
            return null;
        }catch(BaseException baseException){
            return new BaseResponse<>(baseException.getStatus());
        }
    }

}
