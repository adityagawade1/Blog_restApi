package com.blog.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenHelper {
	
	 private static final long token_validity = 5*60*60;
	 
	 private String secret="jwtTokenKey";
	
	 //retrive username from token
	 public String getUsername(String token) {
		 
		 return getClaimFromToken(token, Claims::getSubject);
		 
	 }
	 
	

	//retrive expiration date of token
	 
	 public Date getexpirationDate(String token) {
		 return getClaimFromToken(token,Claims::getExpiration);
	 }

	private <T> T getClaimFromToken(String token,Function<Claims, T>claimsResolver) {
		final Claims claims=getAllClaimsFromToken(token);
		
		return claimsResolver.apply(claims);
	}

    
	 //for retrieving any information from token we will required secret key 

	private Claims getAllClaimsFromToken(String token) {
		// TODO Auto-generated method stub
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
    //check toke is expired?
	 private Boolean isTokenExpired(String token) {
		 final Date expiration= getexpirationDate(token);
		 
		 return expiration.before(new Date());
	 }
	 
	 public String generateToken(UserDetails userDeatails) {
		 Map<String, Object> claims=new HashMap<>();
		 
		 return doGenerateToken(claims, userDeatails.getUsername());
	 }



	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+ token_validity *100)).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
	    final String username = getUsername(token);
	    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	 
	 
	 
	 


}
