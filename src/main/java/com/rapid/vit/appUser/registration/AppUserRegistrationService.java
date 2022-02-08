package com.rapid.vit.appUser.registration;

import com.rapid.vit.appUser.AppUserDetail;
import com.rapid.vit.appUser.AppUserRepo;
import com.rapid.vit.appUser.AppUserRoleRepo;
import com.rapid.vit.config.emailTokenConfirmation.EmailTokenConfirmation;
import com.rapid.vit.config.emailTokenConfirmation.EmailTokenConfirmationService;
import com.rapid.vit.email.AmazonSESService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppUserRegistrationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserRegistrationService.class);

    private final AppUserRepo userRepo;
    private final AppUserRoleRepo userRoleRepo;
    private final EmailTokenConfirmationService emailTokenConfirmationService;
    private final AmazonSESService amazonSESService;

    public AppUserRegistrationService(AppUserRepo userRepo, AppUserRoleRepo userRoleRepo, EmailTokenConfirmationService emailTokenConfirmationService, AmazonSESService amazonSESService) {
        this.userRepo = userRepo;
        this.userRoleRepo = userRoleRepo;
        this.emailTokenConfirmationService = emailTokenConfirmationService;
        this.amazonSESService = amazonSESService;
    }

    /**
     * register the user and send the email to be confirmed in 30 minutes
     * @param appUserDetail
     * @return message information about the registration
     */

    @Transactional
    public Optional<AppUserResponse> register(AppUserDetail appUserDetail) {

        Optional<AppUserResponse> userResponse = Optional.empty();

        Long isUserSavedThenID= userRepo.signUpUser(appUserDetail); //persist the user and get the userID back
        LOGGER.info("Is new user was saved and this is the id "+isUserSavedThenID);
        //generate a token, associate that with the user and persist in db

        int isUserRoleSaved = userRoleRepo.saveUserRole(isUserSavedThenID, appUserDetail.getUserRole().getRoleId());
        LOGGER.info("Is new user role was saved "+isUserRoleSaved);
        appUserDetail.setUserID(isUserSavedThenID);
        LOGGER.info("REGISTER: Before sending the email to the user it, states is {}", appUserDetail);
        userResponse = sendEmailToUser(appUserDetail);

        return userResponse;
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    public String confirmToken(String token) {
        //get the token from db
        EmailTokenConfirmation confirmationToken = emailTokenConfirmationService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        //check whether it was clicked on already
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();
        LocalDateTime tokenConfirmedAt = LocalDateTime.now();

        //check whether token is expired
        if (expiredAt.isBefore(tokenConfirmedAt)) {
            LOGGER.info("token email confirmation date is expired, expiredDate = {} and confirmationDate ={}",expiredAt, tokenConfirmedAt);
            Optional<AppUserDetail> appUserDetail = userRepo.getUserByUserID(confirmationToken.getAppUserID()); //get the user and check if account is enabled
            LOGGER.info("CONFIRM TOKEN WHILE IT EXPIRES: Before sending the email to the user again, it states is {}", appUserDetail);
            if (appUserDetail.isPresent()){
                LOGGER.info("user account is present, let's see if it is also enabled");
                if(appUserDetail.get().isEnabled()){
                    LOGGER.info("user account enabled ");
                    return "Account is confirmed already";
                }else {
                    LOGGER.info("user account NOT enabled ");
                    throw new IllegalStateException("token expired and user account is not enabled");
                }
            }
        }
        //update token confirmation date to db
        int isTokenConfirmed = emailTokenConfirmationService.setConfirmedAt(confirmationToken.getId(), tokenConfirmedAt);
        LOGGER.info("token email confirmation date was updated T/F = {}", isTokenConfirmed);

        //activate or enable user account
        int isUserAccEnabled = userRepo.enableAppUser(confirmationToken.getAppUserID(), true);
        LOGGER.info("app user {} 's account was successfully enabled T/F = {}", confirmationToken.getAppUserID() ,isUserAccEnabled);
        return "confirmed";
    }

    private String emailHtmlBody(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block+\">Hi" + name + ", please confirm your account</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ", please confirm your account.</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n"+ link+
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }

    public Optional<AppUserResponse> sendEmailConfirmationAgain(AppUserDetail appUserDetail) {
        LOGGER.info("SEND EMAIL REGISTRATION AGAIN: Before sending the email to the user, it states is {}", appUserDetail);
        return sendEmailToUser(appUserDetail);
    }

    @Transactional
    public Optional<AppUserResponse> sendEmailToUser(AppUserDetail appUserDetail){
        Optional<AppUserResponse> userResponse = Optional.empty();

        String generatedToken = generateToken(); //create the token to be sent to the user
        LOGGER.info("New Token is generated"+generatedToken);

        //create a email token confirmation object and save the token in db for THIS user
        EmailTokenConfirmation confirmationToken = new EmailTokenConfirmation( //associate it to the user
                appUserDetail.getUserRole().getRoleId()
                ,generatedToken,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15)
        );
        LOGGER.info("New Token is generated inside of the confirmation object"+confirmationToken.toString());

        //persist the token confirmation
        int isTokenConfirmationSaved = emailTokenConfirmationService.saveEmailTokenConfirmation(confirmationToken, appUserDetail.getUserID());
        LOGGER.info("Is new email token confirmation was saved "+isTokenConfirmationSaved);

        //get the link ready for the user to just click on
        String link = "http://localhost:8080/user/confirm?token="+generatedToken;

        LOGGER.info("email token confirmation link is "+link);
        String subject = "Account confirmation email";

        try {
            amazonSESService.sendEmail("lespiva7891@yahoo.es", "Rapidvit", appUserDetail.getUsername(), subject, emailHtmlBody(appUserDetail.getUserFName(), link));
            LOGGER.info("email is sent "+link);
        } catch (MessagingException | UnsupportedEncodingException e) {
            LOGGER.error("email was not sent {} \n----------------------------------------------------------------------", link);
            LOGGER.error("error is : {}", String.valueOf(e));
        }
        String msg = "check your email to confirm your account";
        userResponse = Optional.of(new AppUserResponse(appUserDetail.getUserFName(), appUserDetail.getUsername(), appUserDetail.getUserPhoneNumber(), msg));
        return userResponse;
    }
}
