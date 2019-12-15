package com.able.securitydemo.validator;

import com.able.securitydemo.service.HelloService;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class MyConstraintValidator implements
        ConstraintValidator<MyConstraint,String> {
    @Resource
    HelloService helloService;
    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("MyConstraintValidator init");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        final String s = helloService.queryName(value);
        System.out.println("s = " + s);

        System.out.println(value);
        if (Objects.nonNull(value)&&value.equals("admin")) {
            return false;
        }
        return true;
    }
}
