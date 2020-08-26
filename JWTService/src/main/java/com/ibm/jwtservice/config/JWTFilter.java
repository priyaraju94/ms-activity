package com.ibm.jwtservice.config;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


public class JWTFilter extends GenericFilterBean {

    private TokenService tokenservice;

    public JWTFilter(TokenService tokenservice) {
        this.tokenservice = tokenservice;
    }

  
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
        throws IOException, ServletException {
		/*
		 * Get Token 
		 * Validate Token
		 *  Authenticated??
		 *  If Authenticated -->Set response  to Secutiy context
		 */
        String token = tokenservice.getTokenValue(((HttpServletRequest)request).getHeader(HttpHeaders.AUTHORIZATION));
        if (tokenservice.validateToken(token) && token !=null) {
            Authentication auth = token != null ? tokenservice.getAuthentication(token) : null;
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
        
        
       
    }
}