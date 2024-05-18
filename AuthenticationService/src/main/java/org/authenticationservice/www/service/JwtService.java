package org.authenticationservice.www.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.authenticationservice.www.config.JwtConfigProperties;
import org.authenticationservice.www.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.stereotype.Service;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.*;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class JwtService {
    private final JwtConfigProperties configProperties;

    public String generateToken(String kid, PrivateKey privateKey, UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        Map<String, Object> headers = new HashMap<>();
        if (userDetails instanceof User details) {
            claims.put("sub", details.getId());
        }
        headers.put("typ", "JWT");
        headers.put("kid", kid);

        return generateToken(claims, headers, privateKey, userDetails);
    }

    public String generateToken(Map<String, Object> claims, Map<String, Object> headers, PrivateKey privateKey, UserDetails userDetails) {
        log.info("Subject: {}", userDetails.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setHeader(headers)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + configProperties.getExpirationTime()))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

}
