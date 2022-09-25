package com.example.demo.src.orders;

import com.example.demo.src.orders.dao.AddressDao;
import com.example.demo.src.orders.dao.OrderDao;
import com.example.demo.src.orders.model.PatchAddressRequest;
import com.example.demo.src.orders.model.PostOrderReq;
import com.example.demo.src.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service @RequiredArgsConstructor @Slf4j
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
        try{
            if(req.getSelectAsDefault()){
                userService.setDefaultAddress(req.getBuyerIdx(), addressId);
            }
        }
        catch(Exception e){
            log.error("Something wrong while setting default address {} ", e.getMessage());
        }
        Long orderId = orderDao.createOrder(req, addressId);
        return orderId;
    }

    public Boolean updateOrderStatus(Long orderId, Integer deliveryStatusCode) {
        return orderDao.updateOrderStatus(orderId, deliveryStatusCode) == 1;
    }

    public void updateAddressTo (Long orderId, PatchAddressRequest req) {
        Long addressId = addressDao.createAddress(req.getAddressName(), req.getPostalCode(), req.getAddress1(), req.getAddress2(), req.getReceiverName(), req.getReceiverPhoneNumber());
        orderDao.updateOrderAddress(orderId, addressId);
    }
}
