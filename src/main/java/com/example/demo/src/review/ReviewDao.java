package com.example.demo.src.review;

import com.example.demo.src.review.model.ReviewDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository @RequiredArgsConstructor
public class ReviewDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long createReview(Long productId, Long userIdx, String content,String reviewPhotoUrl,  Short score) {
        String createQuery = "insert into ProductReview(productId, writerIdx, content, reviewPhotoUrl, score, durability, design, price, delivery)" +
                " values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = {productId, userIdx, content, reviewPhotoUrl, score, score, score, score, score};
        jdbcTemplate.update(createQuery, params);
        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }

    public Boolean isReviewIdExist(Long reviewId) {
        String getQuery = "select count(*) from ProductReview where productReviewId = " + reviewId.toString();
        return jdbcTemplate.queryForObject(getQuery, Integer.class) == 1;

    }

    public ReviewDetail findByReviewId(Long reviewId) {
        String getQuery = "select *, (select nickname from User where User.userIdx = ProductReview.writerIdx) as nickname from ProductReview " +
                "inner join Product on Product.productId = ProductReview.productId " +
                "inner join ProductPhoto PP on Product.productId = PP.productId " +
                "where PP.sequenceNo = 0 and productReviewId = ?";
        Object[] params = {reviewId};
        return jdbcTemplate.queryForObject(getQuery, ((rs, rowNum) -> new ReviewDetail(
                rs.getLong("productReviewId"),
                rs.getLong("ProductReview.productId"),
                rs.getLong("writerIdx"),
                rs.getString("nickname"),
                rs.getString("productName"),
                rs.getString("productPhotoUrl"),
                rs.getString("content"),
                rs.getString("reviewPhotoUrl"),
                rs.getInt("score"),
                rs.getInt("durability"),
                rs.getInt("design"),
                rs.getInt("price"),
                rs.getInt("delivery")
        )), params);
    }

    public List<ReviewDetail> findByWriterIdx(Long userIdxByJwt) {
        String getQuery = "select *, (select nickname from User where User.userIdx = ProductReview.writerIdx) as nickname  from ProductReview " +
                "inner join Product on Product.productId = ProductReview.productId " +
                "inner join ProductPhoto PP on Product.productId = PP.productId " +
                "where PP.sequenceNo = 0 and writerIdx = ?";
        Object[] params = {userIdxByJwt};
        return jdbcTemplate.query(getQuery, ((rs, rowNum) -> new ReviewDetail(
                rs.getLong("productReviewId"),
                rs.getLong("ProductReview.productId"),
                rs.getLong("writerIdx"),
                rs.getString("nickname"),
                rs.getString("productName"),
                rs.getString("productPhotoUrl"),
                rs.getString("content"),
                rs.getString("reviewPhotoUrl"),
                rs.getInt("score"),
                rs.getInt("durability"),
                rs.getInt("design"),
                rs.getInt("price"),
                rs.getInt("delivery")
        )), params);
    }

    public List<ReviewDetail> findByProductId(Long productId) {
        String getQuery = "select *, (select nickname from User where User.userIdx = ProductReview.writerIdx) as nickname " +
                " from ProductReview " +
                "inner join Product on Product.productId = ProductReview.productId " +
                "inner join ProductPhoto PP on Product.productId = PP.productId " +
                "where PP.sequenceNo = 0 and PP.productId = ?";
        Object[] params = {productId};
        return jdbcTemplate.query(getQuery, ((rs, rowNum) -> new ReviewDetail(
                rs.getLong("productReviewId"),
                rs.getLong("ProductReview.productId"),
                rs.getLong("writerIdx"),
                rs.getString("nickname"),
                rs.getString("productName"),
                rs.getString("productPhotoUrl"),
                rs.getString("content"),
                rs.getString("reviewPhotoUrl"),
                rs.getInt("score"),
                rs.getInt("durability"),
                rs.getInt("design"),
                rs.getInt("price"),
                rs.getInt("delivery")
        )), params);
    }

    public Boolean updateReview(Long reviewId, Long productId, Long userIdx, String content, String reviewPhotoUrl, Short score) {
        String createQuery = "update ProductReview " +
                "set productId=?, writerIdx=?, content=?, reviewPhotoUrl=?, score=?, durability=?, design=?, price=?, delivery=? " +
                "where productReviewId = ?" ;
        Object[] params = { productId, userIdx, content, reviewPhotoUrl, score, score, score, score, score, reviewId};
        return jdbcTemplate.update(createQuery, params) == 1 ;
    }

    public Boolean deleteReview(Long reviewId) {
        String delQuery = "delete from ProductReview where productReviewId = " + reviewId.toString();
        return jdbcTemplate.update(delQuery) == 1;
    }
}
