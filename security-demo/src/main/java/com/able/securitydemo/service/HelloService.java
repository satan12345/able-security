package com.able.securitydemo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String queryName(String name){
        return name;
    }
}
