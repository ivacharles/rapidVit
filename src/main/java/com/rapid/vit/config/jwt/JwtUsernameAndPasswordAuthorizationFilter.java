package com.rapid.vit.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthorizationFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUsernameAndPasswordAuthorizationFilter.class);
    private final JwtConfig jwtConfig;

    public JwtUsernameAndPasswordAuthorizationFilter(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    //this filter class get the token back from the user for every request, verify it and give the user proper access to the app
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //do not apply the filter to this path cause the user is not accessing anything, he's simply trying to log in. so, let it go through
        if(request.getServletPath().equals("/user/signin")) {
            filterChain.doFilter(request, response);
            return;
        }
        //get the authorization in the request header
        String authorizationHeader = request.getHeader("Authorization");
        //check for null or empty and deny the request
        if(Strings.isEmpty(authorizationHeader) || Strings.isBlank(authorizationHeader)|| !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        //get the token and remove the bearer_ into it
        String token = authorizationHeader.replace("Bearer ", "");
        try{
            String secretKey = jwtConfig.getSecretKey();

            Jws<Claims> claimsJwts = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(token);
            Claims tokenBody = claimsJwts.getBody();
            LOGGER.info("At this point the token is valid, here it is -> {}", tokenBody);
            String username = tokenBody.getSubject();
            //get authorities from the token body
            List<Map<String, String>> authorities = (List<Map<String, String>>) tokenBody.get("authorities");
            //convert the string to simpleGrantedAuthority
            LOGGER.info("This is inside of the token verifier filter, the user is {} and its authority is {}", username,authorities);
            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream().map(m -> new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
            //authenticate the user base on these data from the token
            Authentication authentication = new UsernamePasswordAuthenticationToken(username,"",simpleGrantedAuthorities);
            //tell spring security to give the user access to the app base on its authorities
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }catch (JwtException e){
            throw  new IllegalStateException(String.format("Token %s cannot be trusted",token));
        }
        filterChain.doFilter(request, response);
    }
}
