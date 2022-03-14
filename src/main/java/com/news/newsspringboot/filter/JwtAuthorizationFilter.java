package com.news.newsspringboot.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.news.newsspringboot.config.SecurityConfig;
import com.news.newsspringboot.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * token鉴权
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.userService = userService;
    }

    UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(SecurityConfig.HEADER_STRING);
        //没有token或token错误
        if (header == null || !header.startsWith(SecurityConfig.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        //验证token
        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
        if (header != null) {
            String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()))
                    .build()
                    .verify(header.replace(SecurityConfig.TOKEN_PREFIX, ""))
                    .getSubject();
            if (username != null) {
                //User user = userService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
            }
        }
        return null;
    }
}