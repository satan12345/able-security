package com.able.securitydemo.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Slf4j
@Component
public class TimeInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        request.setAttribute("startTime",System.currentTimeMillis());
        HandlerMethod handlerMethod= (HandlerMethod) handler;
        System.out.println("类名:"+handlerMethod.getBean().getClass().getName());
        System.out.println("方法名:"+handlerMethod.getMethod().getName());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandler");
         Long startTime = (Long) request.getAttribute("startTime");
        log.info("HandlerInterceptor 耗时: {}",(System.currentTimeMillis()-startTime));

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        Long startTime = (Long) request.getAttribute("startTime");
        log.info("HandlerInterceptor 耗时: {}",(System.currentTimeMillis()-startTime));

        log.info("afterCompletion");
        log.info("ex is ",ex);

    }
}
