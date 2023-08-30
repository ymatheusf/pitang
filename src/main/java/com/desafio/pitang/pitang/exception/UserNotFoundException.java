package com.desafio.pitang.pitang.exception;

import java.io.Serial;

public class UserNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1149241039409861914L;


    public UserNotFoundException(String msg){
        super(msg);
    }


    public UserNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}