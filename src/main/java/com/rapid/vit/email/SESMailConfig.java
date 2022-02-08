package com.rapid.vit.email;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rapid.vit.mail")
public class SESMailConfig {
    private String mailHost;
    private String username;
    private String password;
    private String configSet;
    private String port;
    private String emailFrom;
    private String senderName;

    public SESMailConfig() {
    }

    public String getMailHost() {
        return mailHost;
    }

    public void setMailHost(String mailHost) {
        this.mailHost = mailHost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfigSet() {
        return configSet;
    }

    public void setConfigSet(String configSet) {
        this.configSet = configSet;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String email) {
        this.emailFrom = email;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }
}
