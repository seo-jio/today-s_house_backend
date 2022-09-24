package com.example.demo.src.orders;

import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.orders.model.PostOrderRes;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.user.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserProvider userProvider;
    private final ProductProvider productProvider;


    @PostMapping()
    public BaseResponse<?> createNewPost(@RequestBody PostOrderReq req){
        if(!productProvider.isProductExist(req.getProductId()))
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_NOT_FOUND);
        if (!productProvider.isProductOptionExist(req.getProductId(), req.getProductOptionId())) {
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_OPTION_NOT_FOUND);
        }
        // if(!userProvider.isExist(req.))
        //     return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);

        Long orderId = orderService.createOrder(req);
        return new BaseResponse<>(new PostOrderRes(orderId));
    }
}
