package com.example.demo.src.product;

import com.example.demo.src.product.dao.ExpPhotoDao;
import com.example.demo.src.product.dao.ProductDao;
import com.example.demo.src.product.dao.ProductOptionDao;
import com.example.demo.src.product.dao.ProductPhotoDao;
import com.example.demo.src.product.model.PostProductReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductDao productDao;
    private final ProductPhotoDao productPhotoDao;
    private final ExpPhotoDao expPhotoDao;
    private final ProductOptionDao optionDao;
    @Transactional
    public Long createProduct(PostProductReq req) {
        if(req.getIsTodayDeal().length() > 1){
            req.setIsTodayDeal("F");
        }
        Long id = productDao.createProduct(req);
        for(int i =0; i<req.getExpPhotos().size(); i++)
            expPhotoDao.createExpPhoto(id, req.getExpPhotos().get(i), i);
        for(int i =0; i<req.getProductPhotos().size(); i++)
            productPhotoDao.createProductPhoto(id, req.getProductPhotos().get(i), i);
        for(int i =0; i<req.getOptionNames().size(); i++)
            optionDao.createProductOption(id, req.getOptionNames().get(i), req.getOptionPrices().get(i), i);
        return id;
    }
}
