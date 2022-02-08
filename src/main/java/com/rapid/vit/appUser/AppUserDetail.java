package com.rapid.vit.appUser;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

public class AppUserDetail implements UserDetails {

    private Long userID;
    private String userFName;
    private String userLName;
    private String userPhoneNumber;
    private String userEmail;
    private String userPwd;
    private AppUserRole userRole;
    private boolean isUserLocked = false;
    private boolean isUserEnabled = false;
    private LocalDateTime userCreatedDate;


    public AppUserDetail(String userFName, String userLName, String userPhoneNumber, String userEmail, String userPwd, AppUserRole userRole) {
        this.userFName = userFName;
        this.userLName = userLName;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userRole = userRole;
    }

    public AppUserDetail(Long userID, String userFName, String userLName, String userPhoneNumber, String userEmail, String userPwd, AppUserRole userRole, boolean isUserLocked, boolean isUserEnabled, LocalDateTime userCreatedDate) {
        this.userID = userID;
        this.userFName = userFName;
        this.userLName = userLName;
        this.userPhoneNumber = userPhoneNumber;
        this.userEmail = userEmail;
        this.userPwd = userPwd;
        this.userRole = userRole;
        this.isUserLocked = isUserLocked;
        this.isUserEnabled = isUserEnabled;
        this.userCreatedDate = userCreatedDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(userRole.getRoleName());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return userPwd;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { //is used to verified user account
        return !isUserLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() { //is used to activate user account
        return this.isUserEnabled;
    }

    public String getUserFName() {
        return userFName;
    }

    public String getUserLName() {
        return userLName;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public LocalDateTime getUserCreatedDate() {
        return userCreatedDate;
    }

    public Long getUserID() {
        return userID;
    }
    public void setUserRole(AppUserRole userRole) {
        this.userRole = userRole;
    }

    public AppUserRole getUserRole() {
        return userRole;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }


    @Override
    public String toString() {
        return "AppUserDetail{" +
                "userID=" + userID +
                ", userFName='" + userFName + '\'' +
                ", userLName='" + userLName + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userRole=" + userRole +
                ", isUserNonLocked=" + isUserLocked +
                ", isEnabled=" + isEnabled() +
                ", userCreatedDate=" + userCreatedDate +
                '}';
    }
}
