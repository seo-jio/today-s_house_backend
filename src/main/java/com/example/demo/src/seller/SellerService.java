package com.example.demo.src.seller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class SellerService {
    private final SellerDao sellerDao;
    public Boolean isSellerExist(Long sellerId){
        return sellerDao.isSellerIdExist(sellerId);
    }

    public Long createNewSeller(String brandName, String brandExplain){
        return sellerDao.createSeller(brandName, brandExplain);
    }
}
