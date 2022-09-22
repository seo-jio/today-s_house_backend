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
                rs.getTimestamp(5),
                rs.getTimestamp(6),
                rs.getString(7)
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
                rs.getTimestamp(5),
                rs.getTimestamp(6),
                rs.getString(7)
        ), params);

    }

    public List<Category> findAll(){
        String getQuery = "select * from Category";
        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new Category(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getTimestamp(5),
                rs.getTimestamp(6),
                rs.getString(7)
        ));
    }

    public Long createCategory(PostCategoryReq req, int depth){
        String insertQuery = "insert into Category(parentCategoryId, categoryName, depth) values (?, ?, ?)";
        Object[] params = {req.getParentCategoryId(), req.getCategoryName(), depth};
        jdbcTemplate.update(insertQuery, params);
        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }

    public Boolean isCategoryIdExist(Long categoryId) {
        String getQuery = "select count(*) from Category where categoryId = ?";
        Object[] params = {categoryId};

        return jdbcTemplate.queryForObject(getQuery, Integer.class, params) == 1;
    }

    public Category findByCategoryId(Long categoryId) {
        String getQuery = "select * from Category where categoryId = "+categoryId.toString();
        return jdbcTemplate.queryForObject(getQuery, (rs, rowNum) -> new Category(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getTimestamp(5),
                rs.getTimestamp(6),
                rs.getString(7)
        ));
    }

    public Boolean updateCategory(Long categoryId, String categoryName, Long parentCategoryId, int depth) {
        String updateQuery = "update Category set categoryName = ?, parentCategoryId = ?, depth = ? where categoryId = ?";
        Object[] params = {categoryName, parentCategoryId, depth, categoryId};
        return jdbcTemplate.update(updateQuery, params) == 1;
    }

    public List<Category> findByDepth(Integer depth) {
        String getQuery = "select * from Category where depth = ?";
        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new Category(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getTimestamp(5),
                rs.getTimestamp(6),
                rs.getString(7)
        ), new Object[]{depth});
    }

    public void deleteByCategoryId(Long categoryId) {
        String delQuery = "delete * from Category where categoryId = " + categoryId.toString();
        jdbcTemplate.update(delQuery);
    }
}
