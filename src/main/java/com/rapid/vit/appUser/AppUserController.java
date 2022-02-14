package com.rapid.vit.appUser;

import com.rapid.vit.appUser.registration.AppUserRegistrationForm;
import com.rapid.vit.appUser.registration.AppUserRegistrationService;
import com.rapid.vit.appUser.registration.AppUserResponse;
import com.rapid.vit.appUser.registration.AppUserRoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class AppUserController {

    private final AppUserDetailService userDetailService;
    private final AppUserRegistrationService registrationService;

    @Autowired
    public AppUserController(AppUserDetailService userDetailService, AppUserRegistrationService registrationService) {
        this.userDetailService = userDetailService;
        this.registrationService = registrationService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public AppUserResponse signup(@RequestBody @Valid AppUserRegistrationForm registrationForm){
        return userDetailService.signup(registrationForm)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User already exists"));
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token){
        return registrationService.confirmToken(token);
    }

    @PostMapping("/super/u/add/r/to/u")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public AppUserResponse setUserRole(@RequestBody @Valid AppUserRoleForm appUserRoleForm){
        return userDetailService.setUserToSuperUserRole(appUserRoleForm)
                .orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "user has super role set already"));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_SUPER_USER')")
    public List<AppUserDetail> getAllUsers(){
        return userDetailService.getAllUsers();
    }
    @GetMapping("/test")
    @PreAuthorize("hasRole('ROLE_REGULAR_USER')")
    public String test4RegularUser(){
        return "you pass the test as a regular user";
    }
}
