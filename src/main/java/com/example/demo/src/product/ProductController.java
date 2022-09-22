package com.example.demo.src.product;

import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
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

    @GetMapping("")
    BaseResponse<?> getProductMain(){
        return new BaseResponse<>(productProvider.getMainProducts());
    }

    @PostMapping("")
    BaseResponse<?> makeProduct(){
        return null;
    }

    @GetMapping("/{productId}")
    BaseResponse<?> getProductDetail(@PathVariable Long productId){
        if(!productProvider.isProductExist(productId))
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_NOT_FOUND);

        return new BaseResponse<>(productProvider.findByProductId(productId));
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

}
