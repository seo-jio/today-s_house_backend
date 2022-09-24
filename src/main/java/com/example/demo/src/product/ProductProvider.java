package com.example.demo.src.product;

import com.example.demo.src.category.CategoryService;
import com.example.demo.src.category.model.Category;
import com.example.demo.src.product.dao.ExpPhotoDao;
import com.example.demo.src.product.dao.ProductDao;
import com.example.demo.src.product.dao.ProductOptionDao;
import com.example.demo.src.product.dao.ProductPhotoDao;
import com.example.demo.src.product.model.GetProductDetailRes;
import com.example.demo.src.product.model.GetProductsMainRes;
import com.example.demo.src.product.model.GetSearchRes;
import com.example.demo.src.product.model.ProductThumbnail;
import com.example.demo.src.product.model.entity.ExpPhoto;
import com.example.demo.src.product.model.entity.ProductOption;
import com.example.demo.src.product.model.entity.ProductPhoto;
import com.example.demo.src.user.model.GetUserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    private final ProductOptionDao productOptionDao;
    private final CategoryService categoryService;

    public Boolean isProductExist(Long productId){
        return productDao.isProductIdExist(productId);
    }

    public GetProductsMainRes getMainProducts() {
        List<ProductThumbnail> todaysDealsProducts = productDao.findTodaysDealProducts();
        List<ProductThumbnail> favoriteProducts = productDao.findFavoriteProducts();
        return new GetProductsMainRes(todaysDealsProducts, favoriteProducts);
    }

    public GetProductDetailRes findByProductId(Long productId) {
        GetProductDetailRes base = productDao.findByProductId(productId);

        // 카테고리 정보 조회해서 트리로 만듦.
        List<Category> categoryList = categoryService.getCategoryTree(base.getCategory2());

        // 제품 사진 가져오기
        List<ProductPhoto> productPhotos = productPhotoDao.productPhotos(productId);
        List<String> productPhotoUrls = new ArrayList<>();
        for (ProductPhoto p : productPhotos) productPhotoUrls.add(p.getProductPhotoUrl());

        // 제품 설명 가져오기
        List<ExpPhoto> expPhotos = expPhotoDao.productPhotos(productId);
        List<String> expPhotoUrls = new ArrayList<>();
        for (ExpPhoto p : expPhotos) expPhotoUrls.add(p.getExpPhotoUrl());

        // 제품 선택 옵션 가져오기
        List<ProductOption> options = productOptionDao.findByProductId(productId);

        base.setCategoryList(categoryList);
        base.setProductPhotos(productPhotoUrls);
        base.setExpPhotos(expPhotoUrls);
        base.setOptions(options);

        return base;
    }

    public String orderByMapper(String orderBy){
        Map<String, String> sequenceMapper = new HashMap<>();
        //판매순, 낮은 가격순, 높은 가격순, 리뷰 많은 순, 최신순
        sequenceMapper.put("판매순", "numOrders desc");
        sequenceMapper.put("낮은 가격순", "price inc");
        sequenceMapper.put("높은 가격순", "price desc");
        sequenceMapper.put("리뷰 많은 순", "numReviews desc");
        sequenceMapper.put("최신순", "createdAt desc");

        if(sequenceMapper.containsKey(orderBy))
            orderBy = sequenceMapper.get(orderBy);
        else{
            orderBy = "createdAt desc";
        }
        return orderBy;
    }

    public GetSearchRes findByKeyword(String searchKeyWord, String orderBy, Float filter, GetUserRes usr) {
        searchKeyWord = "%"+searchKeyWord+"%";


        if(filter == null)
            filter = 0f;
        orderBy = orderByMapper(orderBy);
        List<ProductThumbnail> products = productDao.findBySearchKeyWord(searchKeyWord, orderBy, filter);

        return new GetSearchRes(products);
    }


    public GetSearchRes findByCategoryId(Long categoryId, String orderBy, Float filter, GetUserRes user) {

        if(filter == null)
            filter = 0f;
        orderBy = orderByMapper(orderBy);
        List<ProductThumbnail> products = productDao.findByCategoryId(categoryId, orderBy, filter);
        return new GetSearchRes(products);
    }

    public GetSearchRes getTodaysDeals(Long userIdx) {
        List<ProductThumbnail> products = productDao.findTodaysDealProducts();
        return new GetSearchRes(products);
    }


    public boolean isProductOptionExist(Long productId, Long productOptionId) {
        List<ProductOption> byProductId = productOptionDao.findByProductId(productId);
        for(ProductOption option : byProductId){
            if(option.getProductOptionId() == productOptionId)
                return true;
        }
        return false;
    }
}

