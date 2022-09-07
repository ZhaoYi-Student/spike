package com.chen.spike.o_ex;

public class UserNameAlreadyExistException extends RuntimeException{

    public UserNameAlreadyExistException(String message) {
        super(message);
    }
}
