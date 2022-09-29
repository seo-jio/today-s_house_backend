package com.example.demo.src.seller;

import com.example.demo.src.seller.model.SimpleSeller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

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

    public List<SimpleSeller> findByBrandName(String brandName) {
        String getQuery = "Select sellerId, brandName, brandExplain from Seller where brandName like ?";
        Object[] params = {brandName};

        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new SimpleSeller(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
        ), params);
    }

    public List<SimpleSeller> findById(Long sellerId) {
        String getQuery = "Select sellerId, brandName, brandExplain from Seller where sellerId = ?";
        Object[] params = {sellerId};

        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new SimpleSeller(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
        ), params);
    }

    public boolean updateSeller(Long sellerId, String brandName, String brandExplain) {
        String updateQuery = "Update Seller set brandName = ?, brandExplain = ? where sellerId = ?";
        Object[] params = {brandName, brandExplain, sellerId};
        return jdbcTemplate.update(updateQuery, params) == 1;
    }

    public List<SimpleSeller> findAll() {
        String getQuery = "Select sellerId, brandName, brandExplain from Seller";

        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new SimpleSeller(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3)
        ));
    }
}
