package com.example.demo.src.phone;

import com.example.demo.src.phone.model.PhoneAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PhoneAuthDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired //readme 참고
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public PhoneAuth findByPhoneNumber(String phoneNumber){
        String getQuery = "select * from PhoneAuth where phoneNumber like ?";
        Object[] params = {phoneNumber};
        return jdbcTemplate.queryForObject(getQuery, (rs, rowNum) -> new PhoneAuth(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        ), params);
    }

    public Boolean isExist(String phoneNumber){
        String getQuery = "select count(*) from PhoneAuth where phoneNumber like ?";
        Object[] params = {phoneNumber};

        return jdbcTemplate.queryForObject(getQuery, Integer.class, params) == 1;

    }

    public Long createRequest(String phoneNumber, String code) {
        String deleteQuery = "delete from PhoneAuth where phoneNumber like " + phoneNumber;
        jdbcTemplate.update(deleteQuery);

        String insertQuery = "insert into PhoneAuth(phoneNumber, code) values (? , ?)";
        Object[] params = {phoneNumber, code};
        jdbcTemplate.update(insertQuery, params);
        return jdbcTemplate.queryForObject("select last_insert_id()", Long.class);
    }

    public Boolean doAuthenticate(String phoneNumber){
        String updateQuery = "update PhoneAuth set isAuth = 'T' where phoneNumber = ?";
        Object[] params = {phoneNumber};
        return jdbcTemplate.update(updateQuery, params) == 1;
    }


    public boolean isAuthenticated(String phoneNumber) {
        return jdbcTemplate.queryForObject("select count(*) from PhoneAuth where phoneNumber like ? and isAuth like 'T'", Integer.class,phoneNumber) == 1;
    }

    public boolean deletePhoneNumber(String phoneNumber) {
        String delQuery = "delete from PhoneAuth where phoneNumber like ?";
        Object[] params = {phoneNumber};
        return jdbcTemplate.update(delQuery, params) == 1;
    }
}
