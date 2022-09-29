package com.example.demo.src.product;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.category.CategoryService;
import com.example.demo.src.product.model.PostProductReq;
import com.example.demo.src.seller.SellerService;
import com.example.demo.src.user.UserProvider;
import com.example.demo.src.user.UserService;
import com.example.demo.src.user.model.GetUserRes;
import com.example.demo.src.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductProvider productProvider;
    private final ProductService productService;
    private final UserProvider userProvider;
    private final SellerService sellerService;
    private final CategoryService categoryService;

    @GetMapping("")
    BaseResponse<?> getProductMain(){
        return new BaseResponse<>(productProvider.getMainProducts());
    }

    @PostMapping("")
    BaseResponse<?> makeProduct(@RequestBody PostProductReq req){
        if(req.isInValid()){
            return new BaseResponse<>(BaseResponseStatus.REQUEST_ERROR);
        }
        if(!sellerService.isSellerExist(req.getSellerId())){
            return new BaseResponse<>(BaseResponseStatus.SELLER_NOT_FOUND);
        }
        if (!categoryService.isCategoryIdExist(req.getCategory1())) {
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        }
        if (!categoryService.isCategoryIdExist(req.getCategory2())) {
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        }

        Long productId = productService.createProduct(req);

        return new BaseResponse<>(productId);
    }

    @GetMapping("/{productId}")
    BaseResponse<?> getProductDetail(@PathVariable Long productId){
        if(!productProvider.isProductExist(productId))
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_NOT_FOUND);

        return new BaseResponse<>(productProvider.findByProductId(productId));
    }

    @PatchMapping("/{productId}")
    BaseResponse<?> updateProduct(@PathVariable Long productId){
        return null;
    }

    @GetMapping("/search")
    BaseResponse<?> searchByKeyWord(
            @RequestParam(required = true) String searchKeyWord ,
            @RequestParam(required = false) Long userIdx,
            @RequestParam(required = true) String orderBy,
            @RequestParam(required = true) Float filter){

        GetUserRes user = null;
        if(userIdx != null ){
            try{user = userProvider.getUser(userIdx);}
            catch (Exception e){
                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
            }
        }
        return new BaseResponse<>(productProvider.findByKeyword(searchKeyWord, orderBy, filter, user));
    }

    @GetMapping("/category")
    BaseResponse<?> searchByCategory(
            @RequestParam(required = true) Long categoryId,
            @RequestParam(required = false) Long userIdx,
            @RequestParam(required = true) String orderBy,
            @RequestParam(required = true) Float filter
    ){
        if(!categoryService.isCategoryIdExist(categoryId)){
            return new BaseResponse<>(BaseResponseStatus.CATEGORY_NOT_FOUND);
        }
        GetUserRes user = null;
        //TODO : 유저 존재 여부 관련 함수 만들고, 이후에 유저 정보 가져와서 스크랩 여부 확인.

        //        try {
        //            if (userIdx != null && userProvider.getUser(userIdx) == null) {
        //                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        //            }
        //            user = userProvider.getUser(userIdx);
        //        }
        //        catch(BaseException e){
        //            return new BaseResponse<>(e.getStatus());
        //        }
        return new BaseResponse<>(productProvider.findByCategoryId(categoryId, orderBy, filter, user));
    }

    @GetMapping("/todaysdeal")
    public BaseResponse<?> getTodaysDeals(@RequestParam(required = false) Long userIdx){
        return new BaseResponse<>(productProvider.getTodaysDeals(userIdx));
    }


}
