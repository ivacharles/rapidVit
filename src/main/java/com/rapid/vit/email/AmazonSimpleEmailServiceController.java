package com.rapid.vit.email;

import com.rapid.vit.appUser.registration.AppUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/to/send/email")
public class AmazonSimpleEmailServiceController {
    private final AmazonSESService amazonSESService;

    @Autowired
    public AmazonSimpleEmailServiceController(AmazonSESService amazonSESService) {
        this.amazonSESService = amazonSESService;
    }


    @PostMapping("/to")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public AppUserResponse emailSender(@RequestBody @Valid EmailForm emailForm){
        Optional<AppUserResponse> userResponse = Optional.empty();
        try {
            amazonSESService.sendEmail(emailForm.getFrom(),emailForm.getFromName(),emailForm.getTo(),emailForm.getSubject(),emailForm.getHtmlBody(emailForm.getReceiverName()));
            String msg = "email was sent successfully to "+emailForm.getTo();
            userResponse = Optional.of(new AppUserResponse(emailForm.getFromName(), emailForm.getFrom(), null, msg));
        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return userResponse.orElseThrow(() -> new HttpServerErrorException(HttpStatus.NOT_ACCEPTABLE, "email was not sent"));
    }
}
