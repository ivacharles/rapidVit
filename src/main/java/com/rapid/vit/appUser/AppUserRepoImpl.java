package com.rapid.vit.appUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AppUserRepoImpl implements AppUserRepo{
    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserRepoImpl.class);


    private final JdbcTemplate jdbcTemplate;
    private final AppUserRoleRepoImpl userRoleRepo;

    @Autowired
    public AppUserRepoImpl(JdbcTemplate jdbcTemplate, AppUserRoleRepoImpl userRoleRepo) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRoleRepo = userRoleRepo;
    }

    @Override
    public Optional<UserDetails> loadUserByEmail(String email) {
        Optional<UserDetails> userDetails = Optional.empty();
        Optional<AppUserDetail> appUser = findByUsername(email);
        if(appUser.isPresent()){
            userDetails = Optional.of(User.builder()
                    .username(appUser.get().getUsername())
                    .password(appUser.get().getPassword())
                    .roles(appUser.get().getUserRole().getRoleName())
                    .accountExpired(false)
                    .accountLocked(appUser.get().isAccountNonLocked())
                    .credentialsExpired(false)
                    .disabled(!appUser.get().isEnabled())
                    .build());
        }
        return  userDetails;
    }

    @Override
    public Optional<AppUserDetail> findByUsername(String username) {
        LOGGER.info("findByUsername method start here "+username);
        Optional<Object> userDetails = Optional.empty();
        String sql = "SELECT * FROM rapidvit_user WHERE user_email_address = ?";
        Optional<AppUserDetail> userDetail = jdbcTemplate.query(sql, new AppUserMapper(),username)
                .stream().findFirst();
        LOGGER.info("here is the return size "+ userDetail +" is present => "+ (userDetail.isPresent()));
        if(userDetail.isPresent()){
            Optional<UserRole> userRole = userRoleRepo.findRoleForUser(userDetail.get().getUserID());
            LOGGER.info("here is the user_role is {}", userRole);
            if(userRole.isPresent()){
                Optional<AppUserRole> appUserRoleObj = userRoleRepo.findRoleByRoleID(userRole.get().getRoleID());
                LOGGER.info("here is  the user_app_role is {} ",appUserRoleObj);
                appUserRoleObj.ifPresent(appUserRole -> userDetail.get().setUserRole(appUserRoleObj.get()));
                LOGGER.info("here is  the user grand authority is {} ",userDetail.get().getAuthorities());
            }
        }
        LOGGER.info("findByUsername method here and "+username+" details are "+userDetail.toString());
        return userDetail;
    }

    @Override
    public Long signUpUser(AppUserDetail appUserDetail) {
        Timestamp accountCreatedDate = Timestamp.valueOf(LocalDateTime.now());
        String sql = "INSERT INTO rapidvit_user(user_f_name, user_l_name, user_phone_number, user_email_address, user_pwd, isUserVerified, isUserActivated, user_account_created_date) VALUES(?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"user_id"});
            ps.setString(1, appUserDetail.getUserFName());
            ps.setString(2, appUserDetail.getUserLName());
            ps.setString(3, appUserDetail.getUserPhoneNumber());
            ps.setString(4, appUserDetail.getUsername());
            ps.setString(5, appUserDetail.getPassword());
            ps.setBoolean(6, appUserDetail.isAccountNonLocked());
            ps.setBoolean(7, appUserDetail.isEnabled());
            ps.setTimestamp(8, accountCreatedDate);
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }


    @Override
    public int enableAppUser(Long userID, boolean appUserAccountEnable ) {
        String sql = "UPDATE rapidvit_user SET isUserActivated = ? WHERE user_id = ?";
        return jdbcTemplate.update(sql,appUserAccountEnable, userID);
    }

    @Override
    public Optional<AppUserDetail> getUserByUserID(Long appUserID) {
        String sql = "SELECT * FROM rapidvit_user WHERE user_id = ?";
        return jdbcTemplate.query(sql, new AppUserMapper(), appUserID)
                .stream().findFirst();
    }
}
