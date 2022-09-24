package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.orders.model.OrderDetail;
import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.orders.model.PostOrderRes;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.user.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserProvider userProvider;
    private final ProductProvider productProvider;
    private final OrderProvider orderProvider;

    @GetMapping("getModel")
    public PostOrderReq makeOrderReq(){
        return new PostOrderReq();
    }

    @PostMapping()
    public BaseResponse<?> createNewPost(@RequestBody PostOrderReq req){
        if(!productProvider.isProductExist(req.getProductId()))
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_NOT_FOUND);
        if (!productProvider.isProductOptionExist(req.getProductId(), req.getProductOptionId())) {
            return new BaseResponse<>(BaseResponseStatus.PRODUCT_OPTION_NOT_FOUND);
        }
        try {
            if (!userProvider.isExist(req.getBuyerIdx()))
                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        Long orderId = orderService.createOrder(req);
        return new BaseResponse<>(new PostOrderRes(orderId));
    }

    @GetMapping("/{orderId}")
    public BaseResponse<?> getOrderDetail(@PathVariable Long orderId){
        if(!orderProvider.isOrderIdExist(orderId))
            return new BaseResponse<>(BaseResponseStatus.ORDER_NOT_FOUND);
        return new BaseResponse<> (orderProvider.findOrderById(orderId));
    }

    @GetMapping("/user/{userIdx}")
    public BaseResponse<?> getOrdersByUserIdx(@PathVariable Long userIdx){
        try {
            if (!userProvider.isExist(userIdx))
                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(orderProvider.findOrdersByBuyerIdx(userIdx));


    }
}