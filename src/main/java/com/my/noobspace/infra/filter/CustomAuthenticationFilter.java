package com.my.noobspace.infra.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.noobspace.modules.account.Account;
import com.my.noobspace.modules.account.AccountRepository;
import com.my.noobspace.utils.CommonUtil;
import com.my.noobspace.utils.CookieUtil;
import com.my.noobspace.utils.ReturnObject;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

// Form based 인증 방식을 사용할 때 사용되는 상속한 커스텀 인증 필터
@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    // 유저 인증을 담당할 인터페이스
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;

    // 원하는 시점에서 로그인 하기위해 authenticationManager를 외부에서 주입받음
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, AccountRepository accountRepository){
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
    }

    // 유저 인증
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        log.info("email is : {}", email);
        log.info("Password is : {}", password);

        // 요청에서 받아온 유저 정보로 토큰 발급
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

        // 토큰으로 인증 수행하고 결과 반환
        return authenticationManager.authenticate(authenticationToken);
    }

    // 인증 성공시
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();

        Account account_domain = new Account();
        account_domain.setEmail(user.getUsername());
        account_domain.setPassword(user.getPassword());

        Account accountObj = accountRepository.findByEmail(user.getUsername());

        Map<String, Object> token = CommonUtil.getToken(accountObj, request);

        Account accountDetail = accountRepository.findByEmail(user.getUsername());

        Map<String, Object> resultObject = new HashMap<>();

        resultObject.put("access_token", token.get("access_token"));
        resultObject.put("email", accountDetail);

        String encodedValue = URLEncoder.encode("Bearer " + token.get("refresh_token"), "UTF-8") ;

        Cookie refresh_token = CookieUtil.addCookie("refresh_token", encodedValue);

        response.addCookie(refresh_token);

        response.setContentType(APPLICATION_JSON_VALUE);
        ReturnObject returnObject = ReturnObject.builder().success(true).data(resultObject).build();
        new ObjectMapper().writeValue(response.getOutputStream(), returnObject);
    }
}