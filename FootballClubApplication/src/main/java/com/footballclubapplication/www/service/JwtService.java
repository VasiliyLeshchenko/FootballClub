package com.footballclubapplication.www.service;

import com.footballclubapplication.www.util.PublicKeyProvider;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.util.Base64URL;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.JoseHeaderNames;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.oauth2.jwt.Jwt;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtService {
    private final PublicKeyProvider publicKeyProvider;

    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    public boolean verify(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(extractPublicKey(token))
                    .build()
                    .parseClaimsJws(token);

            return !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private PublicKey extractPublicKey(String token) {
        return publicKeyConvert(publicKeyProvider.getPublicKey(extractKID(token)));
    }

    private String extractKID(String token) {
        try {
            String[] parts = token.split("\\.");
            return JWSHeader.parse(new String(Base64.getDecoder().decode(parts[0]))).getKeyID();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        System.out.println(token);
        return Jwts.parserBuilder().setSigningKey(extractPublicKey(token)).build().parseClaimsJws(token)
                .getBody();
    }

    private PublicKey publicKeyConvert(String publicKey) {
        try {
            log.info("PUBLIC KEY: {}", publicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public Jwt jwt(String token) {
        Claims claims = extractAllClaims(token);
        Date issued = claims.getIssuedAt();
        Date expiration = claims.getExpiration();
        Instant issuedInstant = Instant.ofEpochSecond(issued.getTime());
        Instant expirationInstant = Instant.ofEpochSecond(expiration.getTime());
        return Jwt.withTokenValue(token)
                .issuedAt(issuedInstant)
                .expiresAt(expirationInstant)
                .header(JoseHeaderNames.ALG, "RS256")
                .header(JoseHeaderNames.KID, extractKID(token))
                .subject(claims.getSubject())
                .build();
    }
}
