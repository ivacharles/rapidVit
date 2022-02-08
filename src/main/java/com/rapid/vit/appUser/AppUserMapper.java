package com.rapid.vit.appUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;


@Component
public class AppUserMapper implements RowMapper<AppUserDetail> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserMapper.class);

    @Override
    public AppUserDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long userID = rs.getLong("user_id");
        String userFName = rs.getString("user_f_name");
        String userLName = rs.getString("user_l_name");
        String userPhoneNumber = rs.getString("user_phone_number");
        String userEmail = rs.getString("user_email_address");
        String userPwd = rs.getString("user_pwd");
        boolean userVerified = rs.getBoolean("isUserVerified");
        boolean userActivated = rs.getBoolean("isUserActivated");
        LocalDateTime accountCreated = rs.getTimestamp("user_account_created_date").toLocalDateTime();

        AppUserDetail appUserDetail = new AppUserDetail(
                userID,userFName,userLName,userPhoneNumber,userEmail,
                userPwd, null,userVerified,userActivated,accountCreated);

        LOGGER.info("this is inside of the row mapper and the app user details info -> {}", appUserDetail);

        return appUserDetail;
    }
}
