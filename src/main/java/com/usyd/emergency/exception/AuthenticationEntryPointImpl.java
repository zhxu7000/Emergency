package com.usyd.emergency.exception;

import com.alibaba.fastjson.JSON;
import com.usyd.emergency.constant.XError;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 处理异常
        ResponseResult result = new ResponseResult(XError.UNAUTHORIZED.getCode(), XError.UNAUTHORIZED.getMsg());
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
