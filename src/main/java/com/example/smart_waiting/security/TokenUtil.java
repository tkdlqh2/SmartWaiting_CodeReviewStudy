package com.example.smart_waiting.security;

<<<<<<< HEAD
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
=======
import com.example.smart_waiting.user.User;
import com.example.smart_waiting.user.service.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
<<<<<<<< HEAD:src/main/java/com/example/smart_waiting/security/TokenProvider.java
========
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
>>>>>>>> 219e4e2 (fix: TokenProvider -> TokenUtil로 이름 변경,):src/main/java/com/example/smart_waiting/security/TokenUtil.java

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TokenUtil {

    private final UserServiceImpl userService;
    private static final long TOKEN_EXPIRE_TIME = 1000*60*60*24; //1DAY
    private static final String KEY_ROLES = "roles";
<<<<<<<< HEAD:src/main/java/com/example/smart_waiting/security/TokenProvider.java
    private final TokenUtil tokenUtil;
========
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";

    private final UserServiceImpl userService;
>>>>>>>> 219e4e2 (fix: TokenProvider -> TokenUtil로 이름 변경,):src/main/java/com/example/smart_waiting/security/TokenUtil.java

    @Value("{spring.jwt.secret}")
    private String secretKey;

    public String generateToken(String username, List<String> roles){
        Claims claims = Jwts.claims().setSubject(username);
        claims.put(KEY_ROLES, roles);

        var now = new Date();
        var expiredDate = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    public Authentication getAuthentication(String jwt){

        UserDetails userDetails = userService.loadUserByUsername(tokenUtil.getEmail(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails,"",userDetails.getAuthorities());
    }

<<<<<<<< HEAD:src/main/java/com/example/smart_waiting/security/TokenProvider.java
========
    public String getEmail(String token){
        return this.parseClaims(token).getSubject();
    }

    public boolean validateToken(String token){
        if(!StringUtils.hasText(token)) return false;

        var claims = this.parseClaims(token);
        return !claims.getExpiration().before(new Date());
    }

    private Claims parseClaims(String token){
>>>>>>> 219e4e2 (fix: TokenProvider -> TokenUtil로 이름 변경,)
        try{
            return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

<<<<<<< HEAD
    public static String resolveTokenFromRequest(HttpServletRequest request){
=======
    public String resolveTokenFromRequest(HttpServletRequest request){
>>>>>>> 219e4e2 (fix: TokenProvider -> TokenUtil로 이름 변경,)
        String token  = request.getHeader(TOKEN_HEADER);

        if(!ObjectUtils.isEmpty(token) && token.startsWith(TOKEN_PREFIX)){
            return token.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
<<<<<<< HEAD
=======
>>>>>>>> 219e4e2 (fix: TokenProvider -> TokenUtil로 이름 변경,):src/main/java/com/example/smart_waiting/security/TokenUtil.java
>>>>>>> 219e4e2 (fix: TokenProvider -> TokenUtil로 이름 변경,)
}
