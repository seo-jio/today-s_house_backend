package com.example.demo.src.product.dao;

import com.example.demo.src.product.model.GetProductDetailRes;
import com.example.demo.src.product.model.ProductThumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ProductThumbnail> findTodaysDealProducts() {
        String getquery = "select * ,\n" +
                "(select p.productPhotoUrl from ProductPhoto p where p.productId = productId and p.sequenceNo = 0) as thumbnailUrl,\n" +
                "(select avg(score) from ProductReview r where r.productId = productId) as totalScore,\n" +
                "(select count(*) from ProductReview r where r.productId = productId) as numReviews,\n" +
                "(select brandName from Seller s where s.sellerId = sellerId) as brandName\n" +
                "from Product where isTodayDeal like ?;";
        Object[] params = {"T"};
        return this.jdbcTemplate.query(getquery,
                (rs, rowNum) -> new ProductThumbnail(
                        rs.getLong("productId"),
                        rs.getString("thumbnailUrl"),
                        rs.getString("productName"),
                        rs.getInt("originalPrice"),
                        rs.getInt("discountedPRice"),
                        null,
                        rs.getFloat("totalScore"),
                        rs.getInt("numReviews"),
                        rs.getString("brandName")
                ),
                params);
    }

    // 그냥 리뷰 개수 순으로 정렬.
    public List<ProductThumbnail> findFavoriteProducts() {
        String getquery = "select * ,\n" +
                "(select p.productPhotoUrl from ProductPhoto p where p.productId = productId and p.sequenceNo = 0) as thumbnailUrl,\n" +
                "(select avg(score) from ProductReview r where r.productId = productId) as totalScore,\n" +
                "(select count(*) from ProductReview r where r.productId = productId) as numReviews,\n" +
                "(select brandName from Seller s where s.sellerId = sellerId) as brandName\n" +
                "from Product order by numReviews desc";
        return this.jdbcTemplate.query(getquery,
                (rs, rowNum) -> new ProductThumbnail(
                        rs.getLong("productId"),
                        rs.getString("thumbnailUrl"),
                        rs.getString("productName"),
                        rs.getInt("originalPrice"),
                        rs.getInt("discountedPRice"),
                        null,
                        rs.getFloat("totalScore"),
                        rs.getInt("numReviews"),
                        rs.getString("brandName")
                ));
    }

    public GetProductDetailRes findByProductId(Long productId) {
        String getQuery = "select * ," +
                "(select avg(score) from ProductReview r where r.productId = productId) as totalScore,\n" +
                "(select count(*) from ProductReview r where r.productId = productId) as numReviews,\n" +
                "(select brandName from Seller s where s.sellerId = sellerId) as brandName\n" +
                "from product where productId = ?";
        Object[] params = {productId};

        return jdbcTemplate.queryForObject(getQuery, (rs,rowNum) -> new GetProductDetailRes(
                null,
                rs.getLong("productId"),
                rs.getString("productName"),
                rs.getFloat("totalScore"),
                rs.getInt("numReviews"),
                rs.getInt("originalPrice"),
                rs.getInt("discountedPrice"),
                rs.getString("brandName"),
                null,
                null
        ),params);
    }

    public Boolean isProductIdExist(Long productId){
        String getQuery = "select count(*) from product where productId = ?";
        Object[] params = {productId};
        return jdbcTemplate.queryForObject(getQuery,Integer.class, params) == 1;
    }

    public List<ProductThumbnail> findBySearchKeyWord(String searchKeyWord, String orderBy, Float filter) {
        String getquery = "select * ,\n" +
                "(select p.productPhotoUrl from ProductPhoto p where p.productId = productId and p.sequenceNo = 0) as thumbnailUrl,\n" +
                "(select avg(score) from ProductReview r where r.productId = productId) as totalScore,\n" +
                "(select count(*) from ProductReview r where r.productId = productId) as numReviews,\n" +
                "(select brandName from Seller s where s.sellerId = sellerId) as brandName, \n" +
                "(select count(*) from Orders o where o.productId = productId) as numOrders \n" +
                "from Product where productName like ? " +
                "and (select avg(score) from ProductReview r where r.productId = productId) > ? " +
                "order by ?";
        Object[] params = {searchKeyWord, orderBy, filter};
        return this.jdbcTemplate.query(getquery,
                (rs, rowNum) -> new ProductThumbnail(
                        rs.getLong("productId"),
                        rs.getString("thumbnailUrl"),
                        rs.getString("productName"),
                        rs.getInt("originalPrice"),
                        rs.getInt("discountedPRice"),
                        null,
                        rs.getFloat("totalScore"),
                        rs.getInt("numReviews"),
                        rs.getString("brandName")
                ), params);
    }
}
