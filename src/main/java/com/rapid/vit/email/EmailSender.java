package com.rapid.vit.email;

public interface EmailSender {
    void sendEmail(String from, String to,String subject, String email);
}
