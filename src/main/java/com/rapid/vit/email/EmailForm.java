package com.rapid.vit.email;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class EmailForm {
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "email invalid")
    private String from;
    @NotBlank
    private String fromName;
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "email invalid")
    private String to;
    @NotBlank
    private String subject;
    @NotBlank
    private String receiverName;
    @NotBlank
    private String htmlBody;

    public String getHtmlBody(String receiverName){
        return this.htmlBody;
    }

    public EmailForm(String from, String fromName, String to, String subject, String receiverName, String htmlBody ) {
        this.from = from;
        this.fromName = fromName;
        this.to = to;
        this.subject = subject;
        this.receiverName = receiverName;
        this.htmlBody = htmlBody;
    }

    public void setHtmlBody(String htmlBody) {
        //must be set with a variable that reference the receiver name
        this.htmlBody = htmlBody;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    @Override
    public String toString() {
        return "EmailForm{" +
                "from='" + from + '\'' +
                ", fromName='" + fromName + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", receiverName='" + receiverName + '\'' +
                '}';
    }
}
