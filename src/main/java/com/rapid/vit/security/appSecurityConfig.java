package com.rapid.vit.security;

import com.rapid.vit.appUser.AppUserDetailService;
import com.rapid.vit.config.jwt.JwtConfig;
import com.rapid.vit.config.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.rapid.vit.config.jwt.JwtUsernameAndPasswordAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class appSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppUserDetailService appUserDetailService;
    private final PasswordEncoder passwordEncoder;
    private final JwtConfig jwtConfig;

    @Autowired
    public appSecurityConfig(AppUserDetailService appUserDetailService, PasswordEncoder passwordEncoder, JwtConfig jwtConfig) {
        this.appUserDetailService = appUserDetailService;
        this.passwordEncoder = passwordEncoder;
        this.jwtConfig = jwtConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtUsernameAndPasswordAuthenticationFilter usernameAndPasswordAuthenticationFilter = new
                JwtUsernameAndPasswordAuthenticationFilter(jwtConfig, authenticationManagerBean());
        usernameAndPasswordAuthenticationFilter.setFilterProcessesUrl("/user/signin");
        // Disable CSRF (cross site request forgery)
        http.csrf().disable();
        // No session will be created or used by spring security
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //add verify login credential filter
        http.addFilter(usernameAndPasswordAuthenticationFilter);
        //allow the entry points
        http.authorizeHttpRequests()
                .antMatchers("/user/signup").permitAll()
                .antMatchers("/user/confirm").permitAll()
                .antMatchers("/listing/**/all").permitAll()
                .antMatchers("/listing/all").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(new JwtUsernameAndPasswordAuthorizationFilter(jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // Allow swagger to be accessed without authentication
        web.ignoring()
                .antMatchers("/swagger-ui/**")
                .antMatchers("/swagger-ui.html")
                .antMatchers("/v3/api-docs/**")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/configuration/**")
                .antMatchers("/webjars/swagger-ui/**")
                .antMatchers("/public");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserDetailService);
        return provider;
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
