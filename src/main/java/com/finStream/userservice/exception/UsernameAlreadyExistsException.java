package com.finStream.userservice.exception;

public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String message){
        super("username " + message + " already exists");
    }
}
