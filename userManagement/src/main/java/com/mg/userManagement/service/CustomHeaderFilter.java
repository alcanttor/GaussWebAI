package com.mg.userManagement.service;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * Created by JavaDeveloperZone on 16-12-2017.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CustomHeaderFilter implements Filter {
   @Override
    public void destroy() {
        System.out.println("destroy filter. release our resources here if any");
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
                                                                                              IOException,ServletException {    
        
        final HttpServletResponse responseComposer = (HttpServletResponse) response;
        responseComposer.setHeader("Access-Control-Allow-Origin", "*");
        responseComposer.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        responseComposer.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, enctype");
        responseComposer.setHeader("Access-Control-Max-Age", "3600");
        if (HttpMethod.OPTIONS.name().equalsIgnoreCase(((HttpServletRequest) request).getMethod())) {
        	responseComposer.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init filter");
    }
}