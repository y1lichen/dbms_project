package com.group18.rental_web.utils;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class JwtUtils {
    Dotenv dotenv = Dotenv.load();

    private String jwtSecret;
    public JwtUtils() {
        this.jwtSecret = dotenv.get("jwtSecret");
    }
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    public String getJwts(String email) {
        Claims claims = Jwts.claims();
        // set jwt lifetime
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 30);
        claims.setIssuedAt(new Date());
        claims.setExpiration(calendar.getTime());
        //
        claims.put("email", email);
        return Jwts.builder().setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public String getEmailFromToken(String token) {
        String email = (String) Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJwt(token).getBody().get("email");
        return email;
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
