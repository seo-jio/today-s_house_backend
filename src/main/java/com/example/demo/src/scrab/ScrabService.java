package com.example.demo.src.scrab;

import com.example.demo.config.BaseException;
import com.example.demo.src.scrab.model.ScrabBanner;
import com.example.demo.src.scrab.model.ScrabItem;
import com.example.demo.src.scrab.model.ScrabProduct;
import com.example.demo.src.user.UserDao;
import com.example.demo.src.user.model.GetUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@RequiredArgsConstructor
public class ScrabService {

    private final ScrabDao scrabDao;
    private final UserDao userDao;
    public List<ScrabItem> getScrabProducts(Long userIdx) throws BaseException{
        try{
            List<ScrabItem> scrabItems = scrabDao.getScrabItemProducts(userIdx);
            return scrabItems;
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public List<ScrabItem> getScrabPhotos(Long userIdx) throws BaseException{
        try{
            List<ScrabItem> scrabItems = scrabDao.getScrabItemPhotos(userIdx);
            return scrabItems;
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void scrabProduct(Long userIdx, Long productId) throws BaseException{
        try{
            scrabDao.scrabProduct(userIdx, productId);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void scrabCancelProduct(Long userIdx, Long productId) throws BaseException{
        try{
            scrabDao.scrabCancelProduct(userIdx, productId);
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void scrabPhoto(Long userIdx, Long photoId) throws BaseException{
        try{
            scrabDao.scrabPhoto(userIdx, photoId);
        }catch(Exception exception){
            System.out.println(exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public void scrabCancelPhoto(Long userIdx, Long photoId) throws BaseException{
        try{
            scrabDao.scrabCancelPhoto(userIdx, photoId);
        }catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public List<ScrabItem> getScrabItems(Long userIdx) throws BaseException{
        try{
            List<ScrabItem> scrabItemsProduct = getScrabProducts(userIdx);
            List<ScrabItem> scrabItemsPhoto = getScrabPhotos(userIdx);
            List<ScrabItem> joinedScrabItems = new ArrayList<>();
            joinedScrabItems.addAll(scrabItemsProduct);
            joinedScrabItems.addAll(scrabItemsPhoto);
            Collections.sort(joinedScrabItems);
            return joinedScrabItems;
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public ScrabBanner getScrabBanner(Long userIdx) throws BaseException{
        try{
            ScrabBanner scrabBanner = new ScrabBanner();
            GetUserRes getUserRes = userDao.getUser(userIdx);
            scrabBanner.setNickname(getUserRes.getNickname());

            List<ScrabItem> scrabItemsProduct = getScrabProducts(userIdx);
            List<ScrabItem> scrabItemsPhoto = getScrabPhotos(userIdx);
            scrabBanner.setScrabProductCount(scrabItemsProduct.size());
            scrabBanner.setScrabPhotoCount(scrabItemsPhoto.size());
            scrabBanner.setScrabTotalCount(scrabItemsProduct.size() + scrabItemsPhoto.size());
            return scrabBanner;
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public List<ScrabProduct> getScrabProductTab(Long userIdx) throws BaseException{
        try{
            List<ScrabProduct> scrabProducts = scrabDao.getScrabProductTab(userIdx);
            return scrabProducts;
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public List<ScrabProduct> getScrabProductTabFilter(Long userIdx, Long categoryId) throws BaseException{
        try{
            List<ScrabProduct> scrabProducts = scrabDao.getScrabProductTabFilter(userIdx, categoryId);
            return scrabProducts;
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public boolean validateScrabProduct(Long userIdx, Long productId) throws BaseException {
        try{
            int count = scrabDao.validateScrabProduct(userIdx, productId);
            if (count == 0){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }

    @Transactional
    public boolean validateScrabPhoto(Long userIdx, Long photoId) throws BaseException {
        try{
            int count = scrabDao.validateScrabPhoto(userIdx, photoId);
            if (count == 0){
                return true;
            }
            else{
                return false;
            }
        }catch(Exception exception){
            System.out.println("exception.getMessage() = " + exception.getMessage());
            throw new BaseException(DATABASE_ERROR);
        }
    }
}
