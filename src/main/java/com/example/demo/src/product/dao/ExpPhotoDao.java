package com.example.demo.src.product.dao;

import com.example.demo.src.product.model.entity.ExpPhoto;
import com.example.demo.src.product.model.entity.ProductPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ExpPhotoDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ExpPhoto> productPhotos(Long productId) {
        String getquery = "select * from ExpPhoto where productId = ?";
        Object[] params = {productId};
        return this.jdbcTemplate.query(getquery,
                (rs, rowNum) -> new ExpPhoto(
                        rs.getLong(1),
                        rs.getLong(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getTimestamp(5),
                        rs.getTimestamp(6),
                        rs.getString(7)
                ),
                params);
    }
}
