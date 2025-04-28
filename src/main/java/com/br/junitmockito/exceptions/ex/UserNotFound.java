package com.br.junitmockito.exceptions.ex;

public class UserNotFound extends RuntimeException{

    private String message;

    public UserNotFound(String message){
        super(message);
    }
}
