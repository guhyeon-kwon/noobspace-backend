package com.my.noobspace.infra.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

// access 권한이 없는 페이지에 접속했을 때 발생하는 AccessDeniedException을 처리하는 handler
@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        ReturnObject returnObject;
        ErrorObject errorObject;

        errorObject = ErrorObject.builder().code("401").message("인증 에러가 발생했습니다.").build();
        returnObject = ReturnObject.builder().success(false).error(errorObject).build();

        //httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out,returnObject);
        //mapper.writeValue(out, response);

        out.flush();
    }
}