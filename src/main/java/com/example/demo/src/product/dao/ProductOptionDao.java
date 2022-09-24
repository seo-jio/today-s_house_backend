package com.example.demo.src.product.dao;

import com.example.demo.src.product.model.entity.ProductOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductOptionDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long createProductOption(Long productId, String optionName, Integer OptionPrice, int i) {
        String createQuery = "Insert into ProductOption(productId, optionName, optionPrice) values (?, ?, ?)";
        Object[] params = {productId, optionName, OptionPrice};
        this.jdbcTemplate.update(createQuery, params);
        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }

    public List<ProductOption> findByProductId(Long productId){
        String getQuery = "Select * from ProductOption where productId = " + productId.toString();
        return jdbcTemplate.query(getQuery, ((rs, rowNum) -> new ProductOption(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getLong(4),
                rs.getTimestamp(5),
                rs.getTimestamp(6),
                rs.getString(7)
        )));
    }
}
