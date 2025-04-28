package com.br.junitmockito.exceptions.ex;

public class UserEmailAlreadyExists extends RuntimeException {
    public UserEmailAlreadyExists(String message) {
        super(message);
    }
}
