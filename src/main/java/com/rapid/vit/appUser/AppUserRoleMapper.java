package com.rapid.vit.appUser;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppUserRoleMapper implements RowMapper<AppUserRole> {
    @Override
    public AppUserRole mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new AppUserRole(
                rs.getLong("role_id"),
                rs.getString("role_name"),
                rs.getString("role_desc")
        );
    }
}
