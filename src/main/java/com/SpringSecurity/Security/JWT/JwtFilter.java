package com.SpringSecurity.Security.JWT;

import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;


public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;



    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal( @NotNull HttpServletRequest request,
                                     @NotNull HttpServletResponse response,
                                     FilterChain filterChain) throws ServletException, IOException {

       String jwtToken= request.getHeader(HttpHeaders.AUTHORIZATION);


        if(jwtToken != null && SecurityContextHolder.getContext().getAuthentication() == null){
            jwtToken=jwtToken.substring(7);
            DecodedJWT decodedJWT= jwtUtil.validateToken(jwtToken);
            String username= jwtUtil.extractUsername(decodedJWT);
            String stringAuthorities= jwtUtil.getClaim(decodedJWT,"authorities").asString();

            Collection<? extends GrantedAuthority> authorities= AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);


            Authentication authentication= new UsernamePasswordAuthenticationToken(username,null,authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        filterChain.doFilter(request,response);
    }
}
