package com.able.securitydemo.web.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeAspect {
//    @Before("")
//
//    @After()
//    @AfterThrowing

    @Pointcut("execution(* com.able.securitydemo.controller.UserController.*(..))")
    public void pointCut(){

    }
    @Around("pointCut()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        log.info("time aspect start");
        long start=System.currentTimeMillis();
        Object object=pjp.proceed();
        final Object[] args = pjp.getArgs();
        log.info("args= {}",args);
        log.info("result={}",object);
        log.info("time aspect 耗时: {}",System.currentTimeMillis()-start);
        log.info("time aspect end");

        return object;
    }

}
