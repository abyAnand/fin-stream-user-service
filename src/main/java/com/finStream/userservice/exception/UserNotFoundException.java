package com.finStream.userservice.exception;


/**
 * This exception is thrown when an attempt is made to retrieve a user that cannot
 * be found in the system. It extends the RuntimeException class, making it an unchecked
 * exception.
 *
 * @param -> message A descriptive message indicating the reason for the user not being found.
 *                This message is included when the exception is thrown.
 */
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
}
