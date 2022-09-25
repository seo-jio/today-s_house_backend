package com.example.demo.src.orders.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
public class AddressDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Transactional
    public Long createAddress(String addressName, String postalCode, String address1, String address2, String receiverName, String receiverPhoneNumber) {
        String createQuery = "Insert into Address(addressName, postalCode, address1, address2, receiverName, receiverPhoneNumber) values(?, ?, ?, ?, ?, ?)";
        Object[] params = {addressName, postalCode, address1, address2, receiverName, receiverPhoneNumber};
        jdbcTemplate.update(createQuery, params);
        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }
}
