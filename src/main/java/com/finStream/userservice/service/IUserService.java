package com.finStream.userservice.service;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.dto.UserRequest;
import com.finStream.userservice.entity.User;

import java.util.UUID;

/**
 * @author Abi Anand <a href="mailto:abianand382@gmail.com"></a>
 * @version  1.0.0
 * @since 1.0.0
 */


public interface IUserService {


    /**
     *
     * @param userRequest the object gives to save
     * @return the newly created user object
     */
    User createUser(UserRequest userRequest);

    /**
     * @param userId is the id of the user
     * @return user with the specified Id
     */

    UserDto getUser(UUID userId);

    /**
     *
     * @param userId the id of the user
     * @param userDto the Dto of the user
     * @return the updated user object
     */

    User updateUser(UUID userId, UserDto userDto);

    /**
     *
     * @param userId of the user to be deleted
     * @return a boolean indicating whether the user is deleted or not
     */

    boolean deleteUser(UUID userId);

}
