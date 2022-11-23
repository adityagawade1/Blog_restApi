package com.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwt;

@Component
public class JwtAuthenticationFilter  extends OncePerRequestFilter{
    
	@Autowired
	private JWTTokenHelper helper;
	
	@Autowired
	private CustomUserDetailService userservice;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String authorizationHeader = request.getHeader("Authorization");
		String username = null;
		        String jwt = null;
		//Taking the header and comparing string Bearer, Space, and extract //JWT.
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
		            jwt = authorizationHeader.substring(7);
		            username = helper.getUsername(jwt);
		        }
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		            UserDetails userDetails = this.userservice.loadUserByUsername(username);
		            if (helper.validateToken(jwt, userDetails)) {
		                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
		                        userDetails, null, userDetails.getAuthorities());
		                usernamePasswordAuthenticationToken
		                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		            }
		        }
		        filterChain.doFilter(request, response);
		    }
		
	

}
