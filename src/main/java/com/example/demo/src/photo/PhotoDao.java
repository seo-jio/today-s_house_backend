package com.example.demo.src.photo;

import com.example.demo.src.photo.model.GetPhotoDetailRes;
import com.example.demo.src.photo.model.PatchPhotoTextReq;
import com.example.demo.src.photo.model.PatchPhotoTypeReq;
import com.example.demo.src.photo.model.PostPhotoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PhotoDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Long createPhoto(PostPhotoReq postPhotoReq) {
        String createPhotoQuery;
        Object[] createPhotoParams;

        if (postPhotoReq.getText() == null){
            createPhotoQuery = "insert into Photo(userIdx, type, photoUrl) values (?, ?, ?)";
            createPhotoParams = new Object[]{postPhotoReq.getUserIdx(), postPhotoReq.getType(), postPhotoReq.getPhotoUrl()};
        }
        else{
            createPhotoQuery = "insert into Photo(userIdx, type, photoUrl, text) values (?, ?, ?, ?)";
            createPhotoParams = new Object[]{postPhotoReq.getUserIdx(), postPhotoReq.getType(), postPhotoReq.getPhotoUrl(), postPhotoReq.getText()};
        }
        this.jdbcTemplate.update(createPhotoQuery, createPhotoParams);

        String lastInsertIdQuery = "select last_insert_id()"; // 가장 마지막에 삽입된(생성된) id값
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, long.class); // 해당 쿼리문의 결과로 마지막으로 삽인된 유저의 userIdx번호를 반환한다.
    }

    public void deletePhoto(Long photoId) {
        String deletePhotoQuery = "update Photo p set status = 'F' where p.photoId = ?";
        Object[] deletePhotoParams = new Object[]{photoId};
        this.jdbcTemplate.update(deletePhotoQuery, deletePhotoParams);
    }

    public void changeType(PatchPhotoTypeReq patchPhotoTypeReq) {
        String changeTypeQuery = "update Photo set type = ? where photoId = ?";
        Object[] changeTypeParams = new Object[]{patchPhotoTypeReq.getType(), patchPhotoTypeReq.getPhotoId()};
        this.jdbcTemplate.update(changeTypeQuery, changeTypeParams);
    }

    public void changeText(PatchPhotoTextReq patchPhotoTextReq) {
        String changeTextQuery = "update Photo set text = ? where photoId = ?";
        Object[] changeTextParams = new Object[]{patchPhotoTextReq.getText(), patchPhotoTextReq.getPhotoId()};
        this.jdbcTemplate.update(changeTextQuery, changeTextParams);
    }

    public GetPhotoDetailRes getPhotoDetail(Long photoId) {
        String getPhotoDetailQuery = "select photoId, userIdx, type, photoUrl, text from Photo where photoId = ?";
        Long getPhotoDetailParam = photoId;
        return jdbcTemplate.queryForObject(getPhotoDetailQuery,
                (rs, rowNum) -> new GetPhotoDetailRes(
                        rs.getLong("photoId"),
                        rs.getLong("userIdx"),
                        rs.getString("type"),
                        rs.getString("photoUrl"),
                        rs.getString("text")),
                getPhotoDetailParam);
    }
}
