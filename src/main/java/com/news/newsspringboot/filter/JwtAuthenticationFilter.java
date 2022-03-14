package com.news.newsspringboot.filter;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.newsspringboot.config.SecurityConfig;
import com.news.newsspringboot.entity.User;
import com.news.newsspringboot.exception.BizException;
import com.news.newsspringboot.exception.ExceptionType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

/**
 * 用户名密码鉴权
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper()
                    .readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>()
                    )
            );
        } catch (IOException e) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = JWT.create()
                .withSubject(((User)authResult.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+ SecurityConfig.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()));
        response.addHeader(SecurityConfig.HEADER_STRING, SecurityConfig.TOKEN_PREFIX+token);
    }
}
