package com.example.demo.src.orders;

import com.example.demo.src.orders.dao.AddressDao;
import com.example.demo.src.orders.dao.OrderDao;
import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


//TODO createOrder부터 작성하기.
@Service @RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;
    private final AddressDao addressDao;
    private final UserService userService;

    @Transactional
    public Long createOrder(PostOrderReq req) {
        Long addressId = addressDao.createAddress(
                req.getAddressName(),
                req.getPostalCode(),
                req.getAddress1(),
                req.getAddress2(),
                req.getReceiverName(),
                req.getReceiverPhoneNumber()
        );
//        if(req.getSelectAsDefault()){
//            userService.setDefaultAddress(req.getBuyerIdx(), addressId);
//        }

        Long orderId = orderDao.createOrder(req, addressId);
        return orderId;
    }
}
