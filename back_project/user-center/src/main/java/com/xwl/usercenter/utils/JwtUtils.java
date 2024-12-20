package com.xwl.usercenter.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @author ruoling
 * @date 2024/12/20 19:38:00
 * @description
 */
public class JwtUtils {
    private static final String SECRET_KEY = UUID.randomUUID().toString().replaceAll("-", "");

    public static String createJwt(Map<String, Object> data, Integer minute) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setExpiration(DateUtils.addMinutes(new Date(), minute))
                .setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString().replaceAll("-", "").toUpperCase())
                .addClaims(data)
                .compact();
    }

    public static Claims getJwt(String token) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder().setSigningKey(secretKey)
                .build().parseClaimsJws(token).getBody();
    }
}
