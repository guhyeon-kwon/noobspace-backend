package com.my.noobspace.infra.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.noobspace.utils.ErrorObject;
import com.my.noobspace.utils.ReturnObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

// 로그인에 실패했을 때 에러 처리를 담당하는 handler
@Component
public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler
{
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse httpServletResponse,
                                        AuthenticationException ex) throws IOException, ServletException
    {
        ErrorObject errorObject = ErrorObject.builder().code("notfound_user").message("해당 유저 정보를 찾을 수 없습니다").build();
        ReturnObject returnObject = ReturnObject.builder().success(false).error(errorObject).build();

        httpServletResponse.setContentType("application/json");
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(out, returnObject);
        out.flush();
    }
}