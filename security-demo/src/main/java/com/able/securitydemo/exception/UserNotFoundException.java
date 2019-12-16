package com.able.securitydemo.exception;

public class UserNotFoundException extends RuntimeException {
    private String id;


    public UserNotFoundException(String id){
        super("user not fount exception");
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
