package com.rapid.vit.appUser.registration;

public class AppUserResponse {
    private String userFName;
    private String userEmail;
    private String userPhoneNumber;
    private String userMessage;

    public AppUserResponse(String userFName, String userEmail, String userPhoneNumber, String userMessage) {
        this.userFName = userFName;
        this.userEmail = userEmail;
        this.userPhoneNumber = userPhoneNumber;
        this.userMessage = userMessage;
    }

    public String getUserFName() {
        return userFName;
    }

    public void setUserFName(String userFName) {
        this.userFName = userFName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }


    @Override
    public String toString() {
        return "AppUserResponse{" +
                "userFName='" + userFName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userMessage='" + userMessage + '\'' +
                '}';
    }
}
