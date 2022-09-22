package com.example.demo.src.category;

import com.example.demo.src.category.model.Category;
import com.example.demo.src.category.model.PostCategoryReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class CategoryDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Boolean isCategoryNameExist(String categoryName){
        String getQuery = "select count(*) from Category where categoryName like ?";
        Object[] params = {categoryName};

        return jdbcTemplate.queryForObject(getQuery, Integer.class, params) == 1;
    }

    public Category findByCategoryName(String categoryName){
        String getQuery = "select * from Category where categoryName like ?";
        Object[] params = {categoryName};

        return jdbcTemplate.queryForObject(getQuery, (rs, rowNum) -> new Category(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getTimestamp(6),
                rs.getTimestamp(7),
                rs.getString(8)
        ), params);
    }

    public List<Category> findByParentCategoryId(Long parentCategoryId){
        String getQuery = "select * from Category where parentCategoryId = ?";
        Object[] params = {parentCategoryId};

        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new Category(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getTimestamp(6),
                rs.getTimestamp(7),
                rs.getString(8)
        ), params);

    }

    public List<Category> findAll(){
        String getQuery = "select * from Category";
        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new Category(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getTimestamp(6),
                rs.getTimestamp(7),
                rs.getString(8)
        ));
    }

    public Long createCategory(PostCategoryReq req, int depth){
        String insertQuery = "insert into Category(parentCategoryId, categoryName, depth) values (?, ?, ?)";
        Object[] params = {req.getParentCategoryId(), req.getCategoryName(), depth};
        jdbcTemplate.update(insertQuery, params);
        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }

}
