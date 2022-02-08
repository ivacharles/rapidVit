package com.rapid.vit.appUser;

import com.rapid.vit.appUser.registration.AppUserRegistrationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;


class AppUserDetailServiceTest {

    @Mock
    private AppUserRepo userRepo;
    @Mock
    private AppUserRoleRepo roleRepo;
    @Mock
    private AppUserRegistrationService registrationService;
    @Mock
    private PasswordEncoder passwordEncoder;
    private AutoCloseable autoCloseable;
    private AppUserDetailService userDetailServiceUnderTest;

    @BeforeEach
    void setUp() {
        //instantiate the mocks
        autoCloseable = MockitoAnnotations.openMocks(this);
        userDetailServiceUnderTest = new AppUserDetailService(userRepo,roleRepo,registrationService,passwordEncoder);
    }

    @AfterEach
    void tearDown() throws Exception {
        //close the mocks after each test
        autoCloseable.close();
    }


    @Test
    @Disabled
    void loadUserByUsername() {
    }

    @Test
    @Disabled
    void signup() {
    }

    @Test
    @Disabled
    void getAllUsers() {
    }

    @Test
    @Disabled
    void setUserToSuperUserRole() {
    }
}