package com.rapid.vit.appUser;

import com.rapid.vit.appUser.registration.AppUserRegistrationForm;
import com.rapid.vit.appUser.registration.AppUserRegistrationService;
import com.rapid.vit.appUser.registration.AppUserResponse;
import com.rapid.vit.appUser.registration.AppUserRoleForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserDetailService implements UserDetailsService {


    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserDetailService.class);

    private final AppUserRepo userRepo;
    private final AppUserRoleRepo roleRepo;
    private final AppUserRegistrationService registrationService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public AppUserDetailService(AppUserRepo userRepo, AppUserRoleRepo roleRepo, AppUserRegistrationService registrationService, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.registrationService = registrationService;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.loadUserByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("user with email %s not found", username)));
    }

    /**
     * Create a new user in the database
     * @param registrationForm
     * @return Optional of user, empty if the user already exists.
     */
    public Optional<AppUserResponse> signup(AppUserRegistrationForm registrationForm) { //registration form coming from user
        LOGGER.info("New user attempting to sign up \nuser details are \n"+registrationForm.toString()); //log this that a user is registering
        Optional<AppUserResponse> userDetail = Optional.empty();

        Optional<AppUserDetail> appUserDetail = userRepo.findByUsername(registrationForm.getUserEmail()); //check is user exists

        LOGGER.info("does user exist -> {}", appUserDetail.isPresent());

        if (!appUserDetail.isPresent()){ // then is user already exists in the database
            LOGGER.info("user: {} does not exists, let us create it",registrationForm.getUserEmail());
            AppUserRole userRole = roleRepo.findByRoleName("REGULAR_USER").get(); //if not, add a regular role for the registering user
            userDetail = registrationService.register(new AppUserDetail( // convert user registration form to userDetail object and delegate it to registration service to register the new user
                    registrationForm.getUserFName(), registrationForm.getUserLName(),
                    registrationForm.getUserPhoneNumber(), registrationForm.getUserEmail(),
                    passwordEncoder.encode(registrationForm.getUserPwd()),
                    userRole
            ));
        }else if(!appUserDetail.get().isEnabled()){
            LOGGER.info("user: {} exists, but not yet confirm the account, let us send an email for user to confirm tha account",registrationForm.getUserEmail());
            userDetail = registrationService.sendEmailConfirmationAgain(appUserDetail.get());
        }

        return userDetail;
    }


    public List<AppUserDetail> getAllUsers() {
        return null;
    }



    public Optional<AppUserResponse> setUserToSuperUserRole(AppUserRoleForm appUserRoleForm) {
        LOGGER.info("super user: {} is setting up user: {} to super user",appUserRoleForm.getAppSuperUserEmail(),appUserRoleForm.getAppUserEmail());

        Optional<AppUserResponse> appUserResponse = Optional.empty();
        Optional<AppUserDetail> appUserDetail = userRepo.findByUsername(appUserRoleForm.getAppUserEmail());
        AppUserRole userRole = roleRepo.findByRoleName("ROLE_SUPER_USER").get();

        if(appUserDetail.isPresent()){
            if(roleRepo.updateUserRole(appUserDetail.get().getUserID(), userRole.getRoleId())==1){
                appUserResponse = Optional.of(new AppUserResponse(appUserDetail.get().getUserFName(), appUserDetail.get().getUsername(), appUserDetail.get().getUserPhoneNumber(), "user: "+appUserDetail.get().getUserFName()+" user_role was successfully updated"));
                LOGGER.info("user: {} user_role was successfully updated",appUserDetail.get().getUsername());
            }
        }
        return appUserResponse;
    }


}
