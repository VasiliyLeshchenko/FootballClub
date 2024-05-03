package com.footballclubapplication.www.service;

import com.footballclubapplication.www.entity.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


//
@Service
public class JwtService {
    private final String SINGING_KEY = "6dc036ad7b19356eb208d335833f5a44e0d61cb5b871a334493d336fb835a40c";
    private final KeyPair KEY_PAIR = Keys.keyPairFor(SignatureAlgorithm.RS256);
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }



    /**
     * Генерация токена
     *
     * @param userDetails данные пользователя
     * @return токен
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof User customUserDetails) {
            claims.put("id", customUserDetails.getId());
            claims.put("email", customUserDetails.getEmail());
            claims.put("role", customUserDetails.getRole());
        }
        return generateToken(claims, userDetails);
    }

    /**
     * Проверка токена на валидность
     *
     * @param token       токен
     * @param userDetails данные пользователя
     * @return true, если токен валиден
     */
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        final String userName = extractUserName(token);
//        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
//    }

    /**
     * Извлечение данных из токена
     *
     * @param token           токен
     * @param claimsResolvers функция извлечения данных
     * @param <T>             тип данных
     * @return данные
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    /**
     * Генерация токена
     *
     * @param extraClaims дополнительные данные
     * @param userDetails данные пользователя
     * @return токен
     */
//    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
//        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
//                .setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
//    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date())
                .signWith(KEY_PAIR.getPrivate(), SignatureAlgorithm.RS256).compact();
    }

    public boolean verify(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(KEY_PAIR.getPublic())
                    .build()
                    .parseClaimsJws(token);

            return true && isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            // В случае ошибки верификации подписи возвращается false
            return false;
        }
    }

    /**
     * Проверка токена на просроченность
     *
     * @param token токен
     * @return true, если токен просрочен
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Извлечение даты истечения токена
     *
     * @param token токен
     * @return дата истечения
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Извлечение всех данных из токена
     *
     * @param token токен
     * @return данные
     */
    private Claims extractAllClaims(String token) {
        System.out.println(token);
        return Jwts.parser().setSigningKey(KEY_PAIR.getPublic()).build().parseClaimsJws(token)
                .getBody();
    }

    /**
     * Получение ключа для подписи токена
     *
     * @return ключ
     */
    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SINGING_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
