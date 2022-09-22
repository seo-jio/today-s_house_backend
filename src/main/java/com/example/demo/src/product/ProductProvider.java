package com.example.demo.src.product;

import com.example.demo.src.product.dao.ExpPhotoDao;
import com.example.demo.src.product.dao.ProductDao;
import com.example.demo.src.product.dao.ProductPhotoDao;
import com.example.demo.src.product.model.GetProductDetailRes;
import com.example.demo.src.product.model.GetProductsMainRes;
import com.example.demo.src.product.model.GetSearchByKeywordRes;
import com.example.demo.src.product.model.ProductThumbnail;
import com.example.demo.src.product.model.entity.ExpPhoto;
import com.example.demo.src.product.model.entity.ProductPhoto;
import com.example.demo.src.user.model.GetUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductProvider {
    private final ProductDao productDao;
    private final ProductPhotoDao productPhotoDao;
    private final ExpPhotoDao expPhotoDao;

    public Boolean isProductExist(Long productId){
        // TODO : DAO 연결해서 구현하기.
        return true;
    }

    public GetProductsMainRes getMainProducts() {
        List<ProductThumbnail> todaysDealsProducts = productDao.findTodaysDealProducts();
        List<ProductThumbnail> favoriteProducts = productDao.findFavoriteProducts();
        return new GetProductsMainRes(todaysDealsProducts, favoriteProducts);
    }

    public GetProductDetailRes findByProductId(Long productId) {
        GetProductDetailRes base = productDao.findByProductId(productId);

        List<ProductPhoto> productPhotos = productPhotoDao.productPhotos(productId);
        List<String> productPhotoUrls = new ArrayList<>();
        for (ProductPhoto p : productPhotos) productPhotoUrls.add(p.getProductPhotoUrl());


        List<ExpPhoto> expPhotos = expPhotoDao.productPhotos(productId);
        List<String> expPhotoUrls = new ArrayList<>();
        for (ExpPhoto p : expPhotos) expPhotoUrls.add(p.getExpPhotoUrl());

        base.setProductPhotos(productPhotoUrls);
        base.setExpPhotos(expPhotoUrls);

        return base;
    }

    public GetSearchByKeywordRes findByKeyword(String searchKeyWord, String orderBy, Float filter, GetUserRes usr) {
        searchKeyWord = "%"+searchKeyWord+"%";

        Map<String, String> sequenceMapper = new HashMap<>();
        //판매순, 낮은 가격순, 높은 가격순, 리뷰 많은 순, 최신순
        sequenceMapper.put("판매순", "numOrders desc");
        sequenceMapper.put("낮은 가격순", "price inc");
        sequenceMapper.put("높은 가격순", "price desc");
        sequenceMapper.put("리뷰 많은 순", "numReviews desc");
        sequenceMapper.put("최신순", "createdAt desc");

        if(filter == null)
            filter = 0f;
        if(sequenceMapper.containsKey(orderBy))
            orderBy = sequenceMapper.get(orderBy);
        else{
            orderBy = "createdAt desc";
        }

        List<ProductThumbnail> products = productDao.findBySearchKeyWord(searchKeyWord, orderBy, filter);

        return new GetSearchByKeywordRes(products, products);
    }


}
