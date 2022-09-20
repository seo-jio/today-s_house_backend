package com.example.demo.src.product;

import com.example.demo.src.product.model.ProductThumbnail;
import com.example.demo.src.user.model.GetUserRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class ProductDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    /*
    *
    Long productId;
    String thumbnailUrl;
    String name;
    Integer originalPrice;
    Integer discountedPrice;
    Boolean isScrabbed;
    Float totalScore;
    Integer numReviews;
    String brandName;
    * */


    public List<ProductThumbnail> findTodaysDealProducts() {
        String getquery = "select * ,\n" +
                "(select p.productPhotoUrl from ProductPhoto p where p.productId = productId and p.sequenceNo = 0) as thumbnailUrl,\n" +
                "(select avg(score) from ProductReview r where r.productId = productId) as totalScore,\n" +
                "(select count(*) from ProductReview r where r.productId = productId) as numReviews,\n" +
                "(select brandName from Seller s where s.sellerId = sellerId) as brandName\n" +
                "from Product where isTodayDeal like 'T';";
        Object[] params = {"T"};
        return this.jdbcTemplate.query(getquery,
                (rs, rowNum) -> new ProductThumbnail(
                        rs.getLong("productId"),
                        rs.getString("thumbnailUrl"),
                        rs.getString("name"),
                        rs.getInt("originalPrice"),
                        rs.getInt("discountedPRice"),
                        Boolean.FALSE,
                        rs.getFloat("totalScore"),
                        rs.getInt("numReviews"),
                        rs.getString("brandName")
                ),
                params); // 해당 닉네임을 갖는 모든 User 정보를 얻기 위해 jdbcTemplate 함수(Query, 객체 매핑 정보, Params)의 결과 반환
    }

    public List<ProductThumbnail> findFavoriteProducts() {
        String getquery = "select * ,\n" +
                "(select p.productPhotoUrl from ProductPhoto p where p.productId = productId and p.sequenceNo = 0) as thumbnailUrl,\n" +
                "(select avg(score) from ProductReview r where r.productId = productId) as totalScore,\n" +
                "(select count(*) from ProductReview r where r.productId = productId) as numReviews,\n" +
                "(select brandName from Seller s where s.sellerId = sellerId) as brandName\n" +
                "from Product order by numReviews desc";
        Object[] params = {"T"};
        return this.jdbcTemplate.query(getquery,
                (rs, rowNum) -> new ProductThumbnail(
                        rs.getLong("productId"),
                        rs.getString("thumbnailUrl"),
                        rs.getString("name"),
                        rs.getInt("originalPrice"),
                        rs.getInt("discountedPRice"),
                        Boolean.FALSE,
                        rs.getFloat("totalScore"),
                        rs.getInt("numReviews"),
                        rs.getString("brandName")
                ),
                params);
    }
}
