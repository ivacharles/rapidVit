package com.rapid.vit.appUser.registration;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class AppUserRegistrationForm {
    @NotBlank(message = "user-first-name must be greater than 3")
    private String userFName;
    @NotBlank(message = "user-last-name must be greater than 3")
    private String userLName;
    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "phone number invalid")
    private String userPhoneNumber;
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "email invalid")
    private String userEmail;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password invalid")
    private String userPwd;


    public AppUserRegistrationForm(String userFName, String userLName, String userPhoneNumber, String userEmail, String userPwd) {
        this.userFName = userFName;
        this.userLName = userLName;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
    }

    public String getUserFName() {
        return userFName;
    }

    public void setUserFName(String userFName) {
        this.userFName = userFName;
    }

    public String getUserLName() {
        return userLName;
    }

    public void setUserLName(String userLName) {
        this.userLName = userLName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    @Override
    public String toString() {
        return "AppUserRegistrationForm{" +
                "userFName='" + userFName + '\'' +
                ", userLName='" + userLName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}

/**
 *Password formation
 * ^ represents starting character of the string.
 * (?=.*[0-9]) represents a digit must occur at least once.
 * (?=.*[a-z]) represents a lower case alphabet must occur at least once.
 * (?=.*[A-Z]) represents an upper case alphabet that must occur at least once.
 * (?=.*[@#$%^&-+=()] represents a special character that must occur at least once.
 * (?=\\S+$) white spaces don’t allowed in the entire string.
 * .{8, 20} represents at least 8 characters and at most 20 characters.
 * $ represents the end of the string.
 */