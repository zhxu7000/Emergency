package com.usyd.emergency.exception;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.usyd.emergency.constant.XError;
import com.usyd.emergency.pojo.ResponseResult;
import com.usyd.emergency.utils.WebUtils;
import org.hibernate.cfg.CreateKeySecondPass;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.persistence.Access;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 处理异常
        ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "permission fault");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response, json);
    }
}
