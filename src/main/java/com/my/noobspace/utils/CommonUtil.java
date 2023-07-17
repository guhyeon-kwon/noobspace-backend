package com.my.noobspace.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.role.Role;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommonUtil {
    private static String SECRET_KEY;
    private static int TOKEN_EXPIRES_TIME;
    private static int REFRESH_TOKEN_EXPIRES_TIME;

    @Value("${property.secretKey}")
    public void setSecretKey(String value){
        SECRET_KEY = value;
    }

    @Value("${property.tokenExpiresTime}")
    public void setTokenExpiresTime(int value){
        TOKEN_EXPIRES_TIME = value;
    }

    @Value("${property.refreshTokenExpiresTime}")
    public void setRefreshTokenExpiresTime(int value){
        REFRESH_TOKEN_EXPIRES_TIME = value;
    }

    public static Map<String, Object> getToken(Account account, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword());

        // 토큰 서명용 키 생성
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY.getBytes());
        // 최초 접속시 발급하는 토큰
        String access_token = JWT.create()
                // 토큰 이름
                .withSubject(account.getEmail())
                // 토큰 만료일
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRES_TIME * 60 * 3000))
                // 토큰 발행자
                .withIssuer(request.getRequestURI().toString())
                // 토큰 payload 작성
                .withClaim("roles", account.getRole().stream().map(Role::getName).collect(Collectors.toList()))
                // 토큰 서명
                .sign(algorithm);

        // access_token을 재발급 받을 수 있는 토큰
        String refresh_token = JWT.create()
                // 토큰 이름
                .withSubject(account.getEmail())
                // 토큰 만료일
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRES_TIME * 60 * 168000))
                // 토큰 발행자
                .withIssuer(request.getRequestURI().toString())
                // 토큰 서명
                .sign(algorithm);

        Map<String, Object> token = new HashMap<>();
        token.put("access_token", access_token);
        token.put("refresh_token", refresh_token);
        return token;
    }
}
