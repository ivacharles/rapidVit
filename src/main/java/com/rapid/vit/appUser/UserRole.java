package com.rapid.vit.appUser;

public class UserRole {
    private Long userID;
    private Long roleID;

    public UserRole(Long userID, Long roleID) {
        this.userID = userID;
        this.roleID = roleID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    @Override
    public String toString() {
        return "UserRole{ userID=" + userID + ", roleID=" + roleID +'}';
    }
}
