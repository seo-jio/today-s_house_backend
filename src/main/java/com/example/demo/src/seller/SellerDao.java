package com.example.demo.src.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class SellerDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    Boolean isSellerIdExist(Long sellerId){
        String getQuery = "select count(*) from Seller where sellerId = " + sellerId.toString();
        return jdbcTemplate.queryForObject(getQuery, Integer.class) == 1;
    }

    public Long createSeller(String brandName, String brandExplain) {
        String createQuery = "Insert into Seller(brandName, brandExplain) values (? , ?)";
        Object[] params = {brandName, brandExplain};
        jdbcTemplate.update(createQuery, params);
        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }
}
