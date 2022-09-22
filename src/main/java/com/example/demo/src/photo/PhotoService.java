package com.example.demo.src.photo;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.photo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoDao photoDao;

    //validation은 try catch문 밖에다가 해야한다.
    public PostPhotoRes createPhoto(PostPhotoReq postPhotoReq) throws BaseException{
        List<String> types = new ArrayList<>(Arrays.asList("L", "B", "K", "LI", "V", "BA", "D", "F"));
        long count = types.stream().filter(type -> postPhotoReq.getType().equals(type)).count();
        if (count == 0){  //유효하지 않은 사진 타입인 경우 validation, 적용 안됨...
            System.out.println("유효하지 않은 사진 타입입니다.");
            throw new BaseException(POST_PHOTO_INVALID_TYPE);
        }
        try{
            Long photoId = photoDao.createPhoto(postPhotoReq);
            return new PostPhotoRes(photoId);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void deletePhoto(Long photoId) throws BaseException{
        try{
            photoDao.deletePhoto(photoId);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void changeType(PatchPhotoTypeReq patchPhotoTypeReq) throws BaseException{
        try{
            List<String> types = new ArrayList<>(Arrays.asList("L", "B", "K", "LI", "V", "BA", "D", "F"));
            long count = types.stream().filter(type -> patchPhotoTypeReq.getType().equals(type)).count();
            System.out.println("count = " + count);
            if (count == 0){  //유효하지 않은 사진 타입인 경우 validation
                throw new BaseException(POST_PHOTO_INVALID_TYPE);
            }
            photoDao.changeType(patchPhotoTypeReq);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void changeText(PatchPhotoTextReq patchPhotoTextReq) throws BaseException{
        try{
            photoDao.changeText(patchPhotoTextReq);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public GetPhotoDetailRes getPhotoDetail(Long photoId) throws BaseException{
        try{
            return photoDao.getPhotoDetail(photoId);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
