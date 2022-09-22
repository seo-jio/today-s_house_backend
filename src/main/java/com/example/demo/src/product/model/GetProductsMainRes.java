package com.example.demo.src.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @RequiredArgsConstructor
public class GetProductsMainRes {
    String[] advertises = {"https://image.ohou.se/i/bucketplace-v2-development/uploads/store/banners/store_home_banners/166332000625903623.jpg?gif=1&w=2560&q=100",
            "https://image.ohou.se/i/bucketplace-v2-development/uploads/store/banners/store_home_banners/166374746282031605.png?gif=1&w=2560&q=100",
            "https://image.ohou.se/i/bucketplace-v2-development/uploads/store/banners/store_home_banners/166374726502983447.png?gif=1&w=2560&q=100",
            "https://image.ohou.se/i/bucketplace-v2-development/uploads/store/banners/store_home_banners/166364662087463093.png?gif=1&w=2560&q=100",
            "https://image.ohou.se/i/bucketplace-v2-development/uploads/store/banners/store_home_banners/166332034741518602.png?gif=1&w=2560&q=100",
            "https://image.ohou.se/i/bucketplace-v2-development/uploads/store/banners/store_home_banners/166332021374458865.jpg?gif=1&w=2560&q=100"};
    final List<ProductThumbnail> todaysDeal;
    final List<ProductThumbnail> favorites;

}
