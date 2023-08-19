package com.usyd.emergency.utils;

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
}
