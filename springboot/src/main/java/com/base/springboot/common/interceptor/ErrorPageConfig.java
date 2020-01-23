package com.base.springboot.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@Component
public class ErrorPageConfig extends HandlerInterceptorAdapter {

    private List<Integer> errorList= Arrays.asList(404, 405, 500);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (errorList.contains(response.getStatus())){
            response.sendRedirect("/error/"+response.getStatus());
            return false;
        }
        return super.preHandle(request, response, handler);
    }
}
