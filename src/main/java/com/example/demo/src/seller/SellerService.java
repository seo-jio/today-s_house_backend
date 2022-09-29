package com.example.demo.src.seller;

import com.example.demo.src.seller.model.SimpleSeller;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class SellerService {
    private final SellerDao sellerDao;
    public Boolean isSellerExist(Long sellerId){
        return sellerDao.isSellerIdExist(sellerId);
    }

    public Long createNewSeller(String brandName, String brandExplain){
        return sellerDao.createSeller(brandName, brandExplain);
    }

    public List<SimpleSeller> findByBrandName(String brandName) {
        return sellerDao.findByBrandName(brandName);
    }

    public List<SimpleSeller> findById(Long sellerId) {
        return sellerDao.findById(sellerId);
    }

    public boolean updateSeller(Long sellerId, String brandName, String brandExplain) {
        return sellerDao.updateSeller(sellerId, brandName, brandExplain);
    }

    public List<SimpleSeller> findAll() {
        return sellerDao.findAll();
    }
}
