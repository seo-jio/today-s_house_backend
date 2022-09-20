package com.example.demo.src.product;

import com.example.demo.src.product.model.GetProductsMainRes;
import com.example.demo.src.product.model.ProductThumbnail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductProvider {
    private final ProductDao productDao;


    public Boolean isProductExist(Long productId){
        // TODO : DAO 연결해서 구현하기.
        return true;
    }

    public GetProductsMainRes getMainProducts() {
        List<ProductThumbnail> todaysDealsProducts = productDao.findTodaysDealProducts();
        List<ProductThumbnail> favoriteProducts = productDao.findFavoriteProducts();
        return new GetProductsMainRes(todaysDealsProducts, favoriteProducts);
    }

}
