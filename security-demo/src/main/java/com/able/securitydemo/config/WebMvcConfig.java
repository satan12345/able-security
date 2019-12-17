package com.able.securitydemo.config;

import com.able.securitydemo.web.filter.TimeFilter;
import com.able.securitydemo.web.interceptor.TimeInterceptor;
import com.google.common.collect.Lists;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Resource
    TimeInterceptor timeInterceptor;
    @Bean
    FilterRegistrationBean timeFilter(){
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new TimeFilter());
        filterRegistrationBean.setUrlPatterns(Lists.newArrayList("/*"));
        return filterRegistrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
        super.addInterceptors(registry);
    }

    /**
     * 用来配异步的支持
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //注册异步的拦截器
//        configurer.registerCallableInterceptors()
//        configurer.registerDeferredResultInterceptors()
        super.configureAsyncSupport(configurer);
    }
}
