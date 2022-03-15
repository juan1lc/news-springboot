package com.news.newsspringboot.config;

import com.news.newsspringboot.exception.RestAuthenticationEntryPoint;
import com.news.newsspringboot.filter.JwtAuthorizationFilter;
import com.news.newsspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SECRET = "LiuChang";
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String CREATE_TOKEN_URL = "/tokens";
    public static final String SIGN_UP_URL = "/users/**";

    UserService userService;
    RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Override
    public void configure(WebSecurity web) throws Exception {
        //利用ignore忽略拦截
        web.ignoring().antMatchers("/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //开启跨域名请求，关闭csrf验证，所有人都可以发出post请求
        //增加JWT鉴权的filter并将sessionManagement更改为无状态
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL, CREATE_TOKEN_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userService))
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password");
    }


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint){
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

}
