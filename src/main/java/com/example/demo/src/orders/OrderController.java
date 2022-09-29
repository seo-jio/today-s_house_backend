package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.orders.model.*;
import com.example.demo.src.product.ProductProvider;
import com.example.demo.src.user.UserProvider;
import com.example.demo.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.example.demo.config.BaseResponseStatus.*;

@RestController @RequestMapping("/api/orders") @RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserProvider userProvider;
    private final ProductProvider productProvider;
    private final OrderProvider orderProvider;
    private final JwtService jwtService;


    @GetMapping("/getModels")
    public BaseResponse<?> getModels(){
        Map<String, Object> models = new HashMap<>();
        models.put("GetOrderThumbnailRes", new GetOrderThumbnailsRes());
        models.put("OrderDetail", new OrderDetail());
        models.put("OrderThumbnail", new OrderThumbnail());
        models.put("PostOrderReq", new PostOrderReq());
        models.put("PostOrderRes", new PostOrderRes());
        models.put("PatchAddressRequest", new PatchAddressRequest());
        return new BaseResponse<>(models);
    }

    @PostMapping()
    public BaseResponse<?> createNewOrder(@RequestBody PostOrderReq req){
        try {
            Long userIdxByJwt = jwtService.getUserIdx();
            if (!req.getBuyerIdx().equals(userIdxByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
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
        Long userIdxByJwt = null;
        try {
            userIdxByJwt = jwtService.getUserIdx();
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        if(!orderProvider.isOrderIdExist(orderId))
            return new BaseResponse<>(BaseResponseStatus.ORDER_NOT_FOUND);
        OrderDetail orderDetail = orderProvider.findOrderById(orderId);
        if(!orderDetail.getBuyerIdx().equals(userIdxByJwt))
            return new BaseResponse<>(INVALID_USER_JWT);
        return new BaseResponse<>(orderDetail);
    }

    @PatchMapping("/{orderId}/status")
    public BaseResponse<?> changeOrderStatus(@PathVariable Long orderId, @RequestParam(required = true) Integer deliveryStatusCode){
        if(!orderProvider.isOrderIdExist(orderId))
            return new BaseResponse<>(BaseResponseStatus.ORDER_NOT_FOUND);
        if(deliveryStatusCode > 5 || deliveryStatusCode < 0)
            return new BaseResponse<>(BaseResponseStatus.WRONG_DELIVERY_CODE);
        orderService.updateOrderStatus(orderId, deliveryStatusCode);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/user/{userIdx}")
    public BaseResponse<?> getOrdersByUserIdx(@PathVariable Long userIdx){
        Long userIdxByJwt = null;
        try {
            userIdxByJwt = jwtService.getUserIdx();
            if (!userIdx.equals(userIdxByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        try {
            if (!userProvider.isExist(userIdx))
                return new BaseResponse<>(BaseResponseStatus.USER_NOT_FOUND);
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        return new BaseResponse<>(orderProvider.findOrdersByBuyerIdx(userIdx));
    }

    @PatchMapping("/{orderId}/address")
    public BaseResponse<?> changeAddress(@PathVariable Long orderId ,@RequestBody PatchAddressRequest req){
        Long userIdxByJwt = null;
        try {
            userIdxByJwt = jwtService.getUserIdx();
        }
        catch(BaseException e){
            return new BaseResponse<>(e.getStatus());
        }
        if(!orderProvider.isOrderIdExist(orderId))
            return new BaseResponse<>(ORDER_NOT_FOUND);
        if(orderProvider.getBuyerIdxByOrderId(orderId) != userIdxByJwt)
            return new BaseResponse<>(INVALID_USER_JWT);
        orderService.updateAddressTo(orderId,req);
        return new BaseResponse<>(SUCCESS);
    }
}