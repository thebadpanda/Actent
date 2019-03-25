package com.softserve.actent.security;

import com.softserve.actent.exceptions.codes.ExceptionCode;
import com.softserve.actent.exceptions.security.NotAuthorizedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        throw new NotAuthorizedException(e.getMessage(), ExceptionCode.NOT_AUTHORIZED);
    }
}
