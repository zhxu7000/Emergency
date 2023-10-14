package com.usyd.emergency.exception;

import com.usyd.emergency.pojo.ResponseResult;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class Handler{

    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public Object conflicExceptionHandler(ConflictException e) {
        return new ResponseResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(FatalException.class)
    @ResponseBody
    public Object fatalExceptionHandler(FatalException e) {
        return new ResponseResult(e.getCode(), e.getMsg());
    }

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseBody
    public Object JsonExceptionHandler(ExpiredJwtException e) {
        return new ResponseResult(-100, e.getMessage());
    }

}
