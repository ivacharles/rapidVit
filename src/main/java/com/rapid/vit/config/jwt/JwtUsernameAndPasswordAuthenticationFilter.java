package com.rapid.vit.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.rapid.vit.appUser.AppUserLoginForm;
import com.rapid.vit.appUser.registration.AppUserResponse;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Date;

public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthenticationFilter.class);

    private final JwtConfig jwtConfig;
    private final AuthenticationManager authenticationManager;

    public JwtUsernameAndPasswordAuthenticationFilter(JwtConfig jwtConfig, AuthenticationManager authenticationManager) {
        this.jwtConfig = jwtConfig;
        this.authenticationManager = authenticationManager;

    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            AppUserLoginForm appUserLoginForm = new ObjectMapper()
                    .readValue(request.getInputStream(), AppUserLoginForm.class);

            LOGGER.info("Username is: {} Password is: {}", appUserLoginForm.getUsername(), appUserLoginForm.getPassword());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    appUserLoginForm.getUsername(), appUserLoginForm.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        String secretKey = jwtConfig.getSecretKey();
        int tokenValidity =jwtConfig.getTokenExpiration();
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(tokenValidity)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        AppUserResponse appUserResponse = new AppUserResponse(authResult.getName(),null,null,token);
        String userResponse = new Gson().toJson(appUserResponse);

        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println(userResponse);
        out.flush();

//        response.addHeader("Authorization","Bearer "+token);
    }
}
