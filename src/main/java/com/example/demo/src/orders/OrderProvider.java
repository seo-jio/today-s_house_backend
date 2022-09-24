package com.example.demo.src.orders;

import com.example.demo.config.BaseResponse;
import com.example.demo.src.orders.dao.OrderDao;
import com.example.demo.src.orders.model.GetOrderDetailsRes;
import com.example.demo.src.orders.model.OrderDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository @RequiredArgsConstructor
public class OrderProvider {
    private final OrderDao orderDao;

    public Boolean isOrderIdExist(Long orderId) {
        return orderDao.isOrderIdExist(orderId);
    }

    public OrderDetail findOrderById(Long orderId) {
        return orderDao.findOrderById(orderId);
    }

    public GetOrderDetailsRes findOrdersByBuyerIdx(Long userIdx) {
        return orderDao.findOrderByBuyerIdx(userIdx);

    }
}
