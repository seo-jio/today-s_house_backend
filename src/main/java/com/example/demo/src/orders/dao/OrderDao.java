package com.example.demo.src.orders.dao;

import com.example.demo.src.orders.model.GetOrderDetailsRes;
import com.example.demo.src.orders.model.OrderDetail;
import com.example.demo.src.orders.model.PostOrderReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class OrderDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Boolean isOrderIdExist(Long orderId) {
        String getQuery = "Select count(*) from Orders where orderId = " + orderId.toString();
        return jdbcTemplate.queryForObject(getQuery, Integer.class) == 1;
    }

    public Long createOrder(PostOrderReq req, Long addressId) {
        String createQuery = "Insert into Orders(" +
                "productId, productOptionId, buyerIdx, " +
                "price, paymentMethod, addressId, request) " +
                "values(?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {
                req.getProductId(),
                req.getProductOptionId(),
                req.getBuyerIdx(),
                req.getPrice(),
                req.getPaymentMethod(),
                addressId,
                req.getRequest()
        };
        jdbcTemplate.update(createQuery, params);

        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }

    public OrderDetail findOrderById(Long orderId) {
        String getQuery = "Select * " +
                "from Orders o " +
                "inner join Product P on o.productId = P.productId " +
                "inner join ProductOption PO on o.productOptionId = PO.productOptionId " +
                "inner join Address A on o.addressId = A.addressId " +
                "inner join User U on o.buyerIdx = U.userIdx " +
                "where o.orderId = ?";
        Object[] params = {orderId};

        return jdbcTemplate.queryForObject(getQuery, (rs, rowNum) -> new OrderDetail(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getLong(4),
                rs.getInt(5),
                rs.getInt(6),
                rs.getString(7),
                rs.getLong(8),
                rs.getString(9),
                rs.getTimestamp(10).toLocalDateTime(),
                rs.getString("P.productName"),
                rs.getString("PO.optionName"),
                rs.getString("U.name"),
                rs.getString("U.email"),
                rs.getString("U.phoneNumber"),
                rs.getString("A.addressName"),
                rs.getString("A.receiverName"),
                rs.getString("A.receiverPhoneNumber"),
                rs.getString("A.address1"),
                rs.getString("A.address2")
        ), params);


    }

    public GetOrderDetailsRes findOrderByBuyerIdx(Long userIdx) {
        return null;
    }
}
