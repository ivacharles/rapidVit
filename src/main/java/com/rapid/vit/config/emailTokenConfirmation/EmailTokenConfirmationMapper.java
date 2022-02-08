package com.rapid.vit.config.emailTokenConfirmation;

import com.rapid.vit.appUser.AppUserDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailTokenConfirmationMapper implements RowMapper<EmailTokenConfirmation> {

    @Override
    public EmailTokenConfirmation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new EmailTokenConfirmation(
                rs.getLong("user_id"),
                rs.getLong("token_id"),
                rs.getString("token"),
                rs.getTimestamp("user_token_created_date").toLocalDateTime(),
                rs.getTimestamp("user_token_expired_date").toLocalDateTime(),
                null
        );
    }
}

