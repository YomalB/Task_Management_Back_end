//package com.task.task.user.service.config;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.crypto.SecretKey;
//import java.util.*;
//
//public class JwtProvider {
//
//    static SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
//
//    public static String generateToken(Authentication auth){
//        Collection<? extends GrantedAuthority> authorities= auth.getAuthorities();
//
//        String roles = populateAuthorities(authorities);
//
//        String jwt= Jwts.builder()
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime()+86400000))
//                .claim("email",auth.getName())
//                .claim("authorities",roles)
//                .signWith(key)
//                .compact();
//
//        return jwt;
//    }
//
//    public static String getEmailFromJWTToken(String jwt){
//        jwt=jwt.substring(7);
//
//
//        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJwt(jwt).getBody();
//
//        String email= String.valueOf(claims.get("email"));
//
//        return email;
//    }
//
//    public static String populateAuthorities( Collection<? extends GrantedAuthority>collection){
//        Set<String> auths=new HashSet<>();
//
//        for (GrantedAuthority authority:collection){
//            auths.add(authority.getAuthority());
//        }
//        return String.join(",",auths);
//    }
//}
package com.task.task.user.service.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.crypto.SecretKey;
import java.util.*;

public class JwtProvider {

    // Generate a secure key of 256 bits
    static SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String generateToken(Authentication auth) {
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        return jwt;
    }

    public static String getEmailFromJWTToken(String jwt) {
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        } else {
            throw new IllegalArgumentException("JWT token does not begin with Bearer String");
        }

        Claims claims;
        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }

        return claims.get("email").toString();
    }

    private static String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }
}
