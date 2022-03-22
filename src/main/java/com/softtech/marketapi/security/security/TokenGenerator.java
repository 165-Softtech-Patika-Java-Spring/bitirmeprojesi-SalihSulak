package com.softtech.marketapi.security.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class TokenGenerator {

    @Value("${softtechspringboot.jwt.security.app.key}")
    private String APP_KEY;

    @Value("${softtechspringboot.jwt.security.expire.time}")
    private Long EXPIRE_TIME;

    public String generateJwtToken(Authentication authentication){
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRE_TIME);

        String token = Jwts.builder()
                .setSubject(customUserDetails.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,APP_KEY)
                .compact();
        return token;
    }

    public UUID findUserByToken(String token){

        Jws<Claims> claimsJws = parseToken(token);
        String userIdStr = claimsJws
                .getBody()
                .getSubject();

        return UUID.fromString(userIdStr);
    }

    private Jws<Claims> parseToken(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(APP_KEY)
                .parseClaimsJws(token);
        return claimsJws;
    }

    public boolean validateToken(String token){
        boolean isValid;

        try {
            Jws<Claims> claimsJws = parseToken(token);

            isValid = !isTokenExpired(claimsJws);
        }
        catch (Exception e){
            isValid = false;
        }
        return isValid;
    }

    private boolean isTokenExpired(Jws<Claims> claimsJws) {
        Date expirationDate = claimsJws.getBody().getExpiration();
        return expirationDate.before(new Date());
    }

}
