package com.my.noobspace.infra.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

// 401(UnAuthorized) 인증 에러가 발생했을 때 처리해주는 로직
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // 인증 에러가 발생했을 때 실행되는 메소드
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        ReturnObject returnObject;
        ErrorObject errorObject;

        errorObject = ErrorObject.builder().code("401").message("인증 에러가 발생했습니다.").build();
        returnObject = ReturnObject.builder().success(false).error(errorObject).build();

//        Map<String,Object> response = new HashMap<>();
//        response.put("status","34");
//        response.put("message","unauthorized access");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, returnObject);
        out.flush();
    }
}