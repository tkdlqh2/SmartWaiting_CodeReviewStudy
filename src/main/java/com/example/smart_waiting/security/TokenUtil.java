package com.example.smart_waiting.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenUtil {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    @Value("{spring.jwt.secret}")
    private static String secretKey;


    public static String getEmail(String token){
        return TokenUtil.parseClaims(token).getSubject();
    }

    public static boolean validateToken(String token){
        if(!StringUtils.hasText(token)) return false;

        var claims = TokenUtil.parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private static Claims parseClaims(String token){
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    public static String resolveTokenFromRequest(HttpServletRequest request){
        String token  = request.getHeader(TOKEN_HEADER);

        if(!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)){
            return token.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}
