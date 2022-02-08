package com.rapid.vit.appUser;

import java.util.Optional;

public interface AppUserRoleRepo {
    Optional<AppUserRole> findByRoleName(String name);

    Optional<UserRole> findRoleForUser(Long userID);

    int saveUserRole(Long userID, Long roleId);

    Optional<AppUserRole> findRoleByRoleID(Long roleID);

    int updateUserRole(Long userID, Long roleId);
}