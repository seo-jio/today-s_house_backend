package com.example.demo.src.scrab;

import com.example.demo.config.BaseException;
import com.example.demo.src.scrab.model.ScrabItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class ScrabService {

    private final ScrabDao scrabDao;
    public List<ScrabItem> getScrabProducts(Long userIdx) throws BaseException{
        try{
            List<ScrabItem> scrabItems = scrabDao.getScrabItemProducts(userIdx);
            return scrabItems;
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<ScrabItem> getScrabPhotos(Long userIdx) throws BaseException{
        try{
            List<ScrabItem> scrabItems = scrabDao.getScrabItemPhotos(userIdx);
            return scrabItems;
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void scrabProduct(Long userIdx, Long productId) throws BaseException{
        try{
            scrabDao.scrabProduct(userIdx, productId);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void scrabCancelProduct(Long userIdx, Long productId) throws BaseException{
        try{
            scrabDao.scrabCancelProduct(userIdx, productId);
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void scrabPhoto(Long userIdx, Long photoId) throws BaseException{
        try{
            scrabDao.scrabPhoto(userIdx, photoId);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public void scrabCancelPhoto(Long userIdx, Long photoId) throws BaseException{
        try{
            scrabDao.scrabCancelPhoto(userIdx, photoId);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
