//package com.cdac.util;
//
//import java.util.Date;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//public class JwtTokenUtil {
//	  private static final String SECRET_KEY = "cafemgmt01"; 
//	    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds
//
//	    public static String generateToken(String username) {
//	        Date now = new Date();
//	        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME);
//
//	        return Jwts.builder()
//	                .setSubject(username)
//	                .setIssuedAt(now)
//	                .setExpiration(expirationDate)
//	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//	                .compact();
//	    }
//
//}
