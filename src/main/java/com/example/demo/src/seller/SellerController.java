package com.example.demo.src.seller;

import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.seller.model.SimpleSeller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/seller") @RequiredArgsConstructor
public class SellerController {
    private final SellerService sellerService;

    @PostMapping("")
    BaseResponse<?> createNewSeller(@RequestBody SimpleSeller simpleSeller){
        Long sellerId = sellerService.createNewSeller(simpleSeller.getBrandName(), simpleSeller.getBrandExplain());
        simpleSeller.setBrandId(sellerId);
        return new BaseResponse<>(simpleSeller);
    }

    @GetMapping("")
    BaseResponse<?> getAllSeller(){
        List<SimpleSeller> simpleSellers = sellerService.findAll();
        return new BaseResponse<>(simpleSellers);
    }

    @GetMapping("/name/{brandName}")
    BaseResponse<?> getSellerByBrandName(@PathVariable String brandName){
        List<SimpleSeller> brands = sellerService.findByBrandName(brandName);
        if(brands.isEmpty())
            return new BaseResponse<>(BaseResponseStatus.BRANDNAME_NOT_FOUND);
        return new BaseResponse<>(brands.get(0));
    }

    @GetMapping("/{sellerId}")
    BaseResponse<?> getSellerByBrandName(@PathVariable Long sellerId){
        List<SimpleSeller> brands = sellerService.findById(sellerId);
        if(brands.isEmpty())
            return new BaseResponse<>(BaseResponseStatus.SELLER_NOT_FOUND);
        return new BaseResponse<>(brands.get(0));
    }

    @PutMapping("/{sellerId}")
    BaseResponse<?> getSellerByBrandName(@PathVariable Long sellerId, @RequestBody SimpleSeller simpleSeller){
        if(!sellerService.isSellerExist(sellerId))
            return new BaseResponse<>(BaseResponseStatus.SELLER_NOT_FOUND);
        if(sellerService.updateSeller(
                sellerId, simpleSeller.getBrandName(), simpleSeller.getBrandExplain()))
            return new BaseResponse<>(BaseResponseStatus.SUCCESS);
        return new BaseResponse<>(BaseResponseStatus.DATABASE_ERROR);
    }
}
