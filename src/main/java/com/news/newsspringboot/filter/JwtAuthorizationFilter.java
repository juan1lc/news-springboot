package com.news.newsspringboot.filter;

import com.news.newsspringboot.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * token鉴权
 */
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    UserService userService;

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String header = request.getHeader(SecurityConfig.HEADER_STRING);
//
//        if (header == null || !header.startsWith(SecurityConfig.TOKEN_PREFIX)) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header);
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        chain.doFilter(request, response);
//    }

//    private UsernamePasswordAuthenticationToken getAuthentication(String header) {
//        if (header != null) {
//            String username = JWT.require(Algorithm.HMAC512(SecurityConfig.SECRET.getBytes()))
//                    .build()
//                    .verify(header.replace(SecurityConfig.TOKEN_PREFIX, ""))
//                    .getSubject();
//            if (username != null) {
//                User user = userService.getUserByUsername(username);
//                return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
//            }
//        }
//        return null;
//    }
}