package com.usyd.emergency.filter;

import com.usyd.emergency.utils.Context;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FilterChainExceptionHandler extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {filterChain.doFilter(request, response);} catch (Exception e) {
            HandlerExceptionResolver resolver
                    = (HandlerExceptionResolver ) Context.getBean("handlerExceptionHandler");
            resolver.resolveException(request, response, null, e);
        }
    }
}
