package com.chen.spike.o_toolkits;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtil {

    private static final String SECRET_KEY = "secret key";

    public static String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpired(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public static boolean isExpired(String token) {
        return extractExpired(token).before(new Date(System.currentTimeMillis()));
    }

    public static String generatorToken(UserDetails userDetails) {
        Map<String, Object> map = new HashMap<>();
        return createToken(map, userDetails.getUsername());
    }

    private static String createToken(Map<String, Object> claims, String userName) {
        final long threeHour = 1000 * 60 * 60 * 3;
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis() + threeHour))
                .setExpiration(new Date(System.currentTimeMillis() + threeHour))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public static boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isExpired(token);
    }

}
