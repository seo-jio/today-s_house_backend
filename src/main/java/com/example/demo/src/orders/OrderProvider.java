package com.example.demo.src.orders;

import com.example.demo.src.orders.dao.OrderDao;
import com.example.demo.src.orders.model.GetOrderThumbnailsRes;
import com.example.demo.src.orders.model.OrderDetail;
import com.example.demo.src.orders.model.OrderThumbnail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository @RequiredArgsConstructor
public class OrderProvider {
    private final OrderDao orderDao;

    public Boolean isOrderIdExist(Long orderId) {
        return orderDao.isOrderIdExist(orderId);
    }

    public OrderDetail findOrderById(Long orderId) {
        return orderDao.findOrderById(orderId);
    }

    public GetOrderThumbnailsRes findOrdersByBuyerIdx(Long userIdx) {
        List<OrderThumbnail> orderThumbnails = orderDao.findOrderByBuyerIdx(userIdx);
        return new GetOrderThumbnailsRes(orderThumbnails);
    }

    public long getBuyerIdxByOrderId(Long orderId){
        return orderDao.getBuyerIdByOrderId(orderId);
    }
}
