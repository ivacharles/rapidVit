package com.rapid.vit.appUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AppUserRoleRepoImpl implements AppUserRoleRepo{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppUserRoleRepoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<AppUserRole> findByRoleName(String roleName) {
        String sql = "SELECT * FROM rapidvit_role WHERE role_name =?";
        return jdbcTemplate.query(sql, new AppUserRoleMapper(), roleName)
                .stream().findFirst();
    }

    @Override
    public Optional<UserRole> findRoleForUser(Long userId) {
        String sql = "SELECT * FROM user_role WHERE user_id =?";
        return jdbcTemplate.query(sql, new AppUserRoleRowMapper(), userId)
                .stream().findFirst();
    }

    @Override
    public int saveUserRole(Long userID, Long roleId) {
        String sql = "INSERT INTO user_role(user_id, role_id) VALUES(?,?)";
        return jdbcTemplate.update(sql,userID, roleId);
    }

    @Override
    public Optional<AppUserRole> findRoleByRoleID(Long roleID) {
        String sql = "SELECT * FROM rapidvit_role WHERE role_id =?";
        return jdbcTemplate.query(sql, new AppUserRoleMapper(), roleID)
                .stream().findFirst();
    }

    @Override
    public int updateUserRole(Long userID, Long roleId) {
        String sql = "UPDATE user_role SET role_id=? WHERE user_id = ?";
        return jdbcTemplate.update(sql,roleId, userID);
    }

}
