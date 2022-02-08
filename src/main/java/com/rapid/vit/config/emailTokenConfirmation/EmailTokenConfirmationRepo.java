package com.rapid.vit.config.emailTokenConfirmation;


import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailTokenConfirmationRepo {

    Optional<EmailTokenConfirmation> findToken(String token);

    int updateConfirmedAt(Long token_id, LocalDateTime user_token_confirmed_date);

    int registerEmailTokenConfirmation(EmailTokenConfirmation confirmationToken, Long userID);
}
