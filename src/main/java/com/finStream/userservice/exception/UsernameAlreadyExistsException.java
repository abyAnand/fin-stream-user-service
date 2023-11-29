package com.finStream.userservice.exception;


/**
 * This exception is thrown when an attempt is made to create a user with a username
 * that already exists in the system. It extends the RuntimeException class,
 * making it an unchecked exception.
 *
 * @param -> message A descriptive message indicating which username already exists.
 *                This message is included when the exception is thrown.
 */
public class UsernameAlreadyExistsException extends RuntimeException{

    public UsernameAlreadyExistsException(String message){
        super("username " + message + " already exists");
    }
}
