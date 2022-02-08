package com.rapid.vit.appUser;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AppUserRepo {

    Optional<UserDetails> loadUserByEmail(String email);

    Optional<AppUserDetail> findByUsername(String userName);

    Long signUpUser(AppUserDetail appUserDetail);

    int enableAppUser(Long userID, boolean enableUserAccount);

    Optional<AppUserDetail> getUserByUserID(Long appUserID);
}
