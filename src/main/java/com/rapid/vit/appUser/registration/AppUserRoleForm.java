package com.rapid.vit.appUser.registration;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AppUserRoleForm {
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "email invalid")
    private String appUserEmail;
    @Email(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "email invalid")
    private String appSuperUserEmail;
    @NotBlank
    private String appUserNewRole;

    public AppUserRoleForm(String appUserEmail, String appUserNewRole, String appSuperUserEmail) {
        this.appUserEmail = appUserEmail;
        this.appUserNewRole = appUserNewRole;
        this.appSuperUserEmail=appSuperUserEmail;
    }

    public String getAppSuperUserEmail() {
        return appSuperUserEmail;
    }

    public void setAppSuperUserEmail(String appSuperUserEmail) {
        this.appSuperUserEmail = appSuperUserEmail;
    }

    public String getAppUserEmail() {
        return appUserEmail;
    }

    public void setAppUserEmail(String appUserEmail) {
        this.appUserEmail = appUserEmail;
    }

    public String getAppUserNewRole() {
        return appUserNewRole;
    }

    public void setAppUserNewRole(String appUserNewRole) {
        this.appUserNewRole = appUserNewRole;
    }
}
