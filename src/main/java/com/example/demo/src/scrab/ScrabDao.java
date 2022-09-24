package com.example.demo.src.scrab;

import com.example.demo.src.scrab.model.ScrabBanner;
import com.example.demo.src.scrab.model.ScrabItem;
import com.example.demo.src.scrab.model.ScrabProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class ScrabDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<ScrabItem> getScrabItemPhotos(Long userIdx){
        String getScrabPhotoQuery = "select s.photoId, p.photoUrl, s.createdAt from Photo p join (select * from ScrabPhoto s where userIdx = ? and s.status = 'T') as s on p.photoId = s.photoId order by s.createdAt desc";
        Object[] getScrabPhotoParams = new Object[]{userIdx};
        Long getScrabPhotoParam = userIdx;
        return jdbcTemplate.query(getScrabPhotoQuery,
                (rs, rowNum) -> new ScrabItem("Photo",
                        rs.getLong("photoId"),
                        rs.getString("photoUrl"),
                        rs.getTimestamp("createdAt").toLocalDateTime()
                ), getScrabPhotoParams);
    }

    public List<ScrabItem> getScrabItemProducts(Long userIdx) {
        String getQuery = "select s.productId, s.createdAt ,(select p.productPhotoUrl from ProductPhoto p where p.productId = p.productId and p.sequenceNo = 0) as imageUrl\n" +
                "                from Product pr, Scrab s where pr.productId = s.productId and s.userIdx = ? and s.status = 'T' order by s.createdAt desc;";
        Object[] params = {userIdx};
        return jdbcTemplate.query(getQuery, (rs, rowNum) -> new ScrabItem(
                "Product",
                rs.getLong("productId"),
                rs.getString("imageUrl"),
                rs.getTimestamp("createdAt").toLocalDateTime()
        ), params);
    }

    public void scrabProduct(Long userIdx, Long productId) {
        String query = "insert into Scrab(userIdx, productId) values (?, ?)";
        Object[] params = new Object[]{userIdx, productId};
        jdbcTemplate.update(query, params);
    }

    public void scrabCancelProduct(Long userIdx, Long productId) {
        String query = "update Scrab set status = 'F' where userIdx = ? and productId = ?";
        Object[] params = new Object[]{userIdx, productId};
        jdbcTemplate.update(query, params);
    }

    public void scrabPhoto(Long userIdx, Long photoId) {
        String query = "insert into ScrabPhoto(userIdx, photoId) values (?, ?)";
        Object[] params = new Object[]{userIdx, photoId};
        jdbcTemplate.update(query, params);
    }

    public void scrabCancelPhoto(Long userIdx, Long photoId) {
        String query = "update ScrabPhoto set status = 'F' where userIdx = ? and photoId = ?";
        Object[] params = new Object[]{userIdx, photoId};
        jdbcTemplate.update(query, params);
    }

    public List<ScrabProduct> getScrabProductTab(Long userIdx) {
        String query = "select *, (select p.productPhotoUrl from ProductPhoto p where p.productId = Product.productId and p.sequenceNo = 0) as thumbnailUrl,\n" +
                "                (select avg(score) from ProductReview r where r.productId = Product.productId) as totalScore,\n" +
                "                (select count(*) from ProductReview r where r.productId = Product.productId) as numReviews,\n" +
                "                (select brandName from Seller s where s.sellerId = Product.sellerId) as brandName\n" +
                "                from Product join Scrab s on s.productId = Product.productId and s.userIdx = ? and s.status = 'T' order by s.createdAt desc";
        Object[] params = new Object[]{userIdx};
        return jdbcTemplate.query(query,
                (rs, rowNum) -> new ScrabProduct(
                        rs.getLong("Product.productId"),
                        rs.getString("thumbnailUrl"),
                        rs.getString("productName"),
                        rs.getInt("originalPrice"),
                        rs.getInt("discountedPrice"),
                        rs.getInt("totalScore"),
                        rs.getInt("numReviews"),
                        rs.getString("brandName")),
                params);
    }

    public List<ScrabProduct> getScrabProductTabFilter(Long userIdx, Long categoryId) {
        String query = "select *, (select p.productPhotoUrl from ProductPhoto p where p.productId = Product.productId and p.sequenceNo = 0) as thumbnailUrl,\n" +
                "                (select avg(score) from ProductReview r where r.productId = Product.productId) as totalScore,\n" +
                "                (select count(*) from ProductReview r where r.productId = Product.productId) as numReviews,\n" +
                "                (select brandName from Seller s where s.sellerId = Product.sellerId) as brandName\n" +
                "                from Product join Scrab s on s.productId = Product.productId and s.userIdx = ? and Product.category1 = ? and s.status = 'T' order by s.createdAt desc";
        Object[] params = new Object[]{userIdx, categoryId};
        return jdbcTemplate.query(query,
                (rs, rowNum) -> new ScrabProduct(
                        rs.getLong("Product.productId"),
                        rs.getString("thumbnailUrl"),
                        rs.getString("productName"),
                        rs.getInt("originalPrice"),
                        rs.getInt("discountedPrice"),
                        rs.getInt("totalScore"),
                        rs.getInt("numReviews"),
                        rs.getString("brandName")),
                params);
    }
}
