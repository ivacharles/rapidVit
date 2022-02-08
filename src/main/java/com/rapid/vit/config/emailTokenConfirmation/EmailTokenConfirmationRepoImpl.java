package com.rapid.vit.config.emailTokenConfirmation;

import com.rapid.vit.appUser.AppUserDetail;
import com.rapid.vit.appUser.AppUserRoleRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class EmailTokenConfirmationRepoImpl implements EmailTokenConfirmationRepo{
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailTokenConfirmationRepoImpl.class);

   private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmailTokenConfirmationRepoImpl( JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<EmailTokenConfirmation> findToken(String tokenString) {
        LOGGER.info("find token by token name method start here and token is {}",tokenString);
        String sql = "SELECT * FROM user_email_token_confirmation WHERE token = ?";
        Optional<EmailTokenConfirmation> tokenConfirmation = jdbcTemplate.query(sql, new EmailTokenConfirmationMapper(), tokenString)
                .stream().findFirst();
        LOGGER.info("here is the return token obj => {} ", tokenConfirmation.toString());
        return tokenConfirmation;
    }

    @Override
    public int updateConfirmedAt(Long token_id, LocalDateTime user_token_confirmed_date) {
        String sql = "UPDATE user_email_token_confirmation SET user_token_confirmed_date = ? WHERE token_id = ?";
        return jdbcTemplate.update(sql,  Timestamp.valueOf(user_token_confirmed_date),token_id);
    }

    @Override
    public int registerEmailTokenConfirmation(EmailTokenConfirmation confirmationToken, Long userID) {
        LOGGER.info("This is inside of register Email Token confirmation and tokenConfirm. = {} ",confirmationToken.toString());

        String sql= "INSERT INTO user_email_token_confirmation(user_id, token, user_token_created_date, user_token_expired_date) VALUES(?,?,?,?)";
        Timestamp user_token_created_date = Timestamp.valueOf(LocalDateTime.now());
        Timestamp user_token_expired_date = Timestamp.valueOf(LocalDateTime.now().plusMinutes(15));
        return jdbcTemplate.update(sql,userID, confirmationToken.getToken()
                ,user_token_created_date, user_token_expired_date);
    }
}
