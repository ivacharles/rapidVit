package com.rapid.vit.config.emailTokenConfirmation;

import com.rapid.vit.appUser.AppUserDetail;

import java.time.LocalDateTime;

public class EmailTokenConfirmation {
    private Long appUserID;
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    public EmailTokenConfirmation(Long appUserID, Long id,  String token, LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime confirmedAt) {
        this.id = id;
        this.appUserID = appUserID;
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.confirmedAt = confirmedAt;
    }

    public EmailTokenConfirmation(Long appUserID, String token, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.token = token;
        this.appUserID = appUserID;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppUserID() {
        return appUserID;
    }

    public void setAppUserID(Long appUserID) {
        this.appUserID = appUserID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }


    @Override
    public String toString() {
        return "EmailTokenConfirmation{" +
                "appUserID=" + appUserID +
                ", id=" + id +
                ", token='" + token + '\'' +
                ", createdAt=" + createdAt +
                ", expiresAt=" + expiresAt +
                ", confirmedAt=" + confirmedAt +
                '}';
    }
}

