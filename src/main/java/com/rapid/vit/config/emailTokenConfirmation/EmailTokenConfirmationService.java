package com.rapid.vit.config.emailTokenConfirmation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailTokenConfirmationService {

    private final EmailTokenConfirmationRepo emailTokenConfirmationRepo;

    @Autowired
    public EmailTokenConfirmationService(EmailTokenConfirmationRepo emailTokenConfirmationRepo) {
        this.emailTokenConfirmationRepo = emailTokenConfirmationRepo;
    }

    public int saveEmailTokenConfirmation(EmailTokenConfirmation confirmationToken, Long userID) {
        return emailTokenConfirmationRepo.registerEmailTokenConfirmation(confirmationToken, userID);
    }

    public int setConfirmedAt(Long token_id, LocalDateTime tokenConfirmationDate) {
        return emailTokenConfirmationRepo.updateConfirmedAt(token_id, tokenConfirmationDate);
    }

    public Optional<EmailTokenConfirmation> getToken(String token) {
        return emailTokenConfirmationRepo.findToken(token);
    }
}
