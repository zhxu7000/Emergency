package com.usyd.emergency.utils;

import com.usyd.emergency.pojo.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class XUtils {

    public static void resolveException(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception e) {
        HandlerExceptionResolver resolver
                = (HandlerExceptionResolver ) Context.getBean("handlerExceptionResolver");
        resolver.resolveException(request, response, null, e);
    }

    public static int getUid() {
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User loginUser = (User) authentication.getPrincipal();
        Integer userId = loginUser.getUserId();
        return userId;
    }

    public static boolean isAdmin() {
        UsernamePasswordAuthenticationToken authentication
                = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User loginUser = (User) authentication.getPrincipal();
        System.out.println(loginUser);
        if (loginUser.getIsAdmin() == "1") return true;
        return false;
    }


}
