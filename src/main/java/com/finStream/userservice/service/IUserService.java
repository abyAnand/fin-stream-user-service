package com.finStream.userservice.service;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.dto.UserRequest;
import com.finStream.userservice.entity.User;

import java.util.List;
import java.util.UUID;

/**
 * The IUserService interface defines the contract for interacting with user-related operations
 * in the application. Implementations of this interface provide functionality for creating, retrieving,
 * updating, and deleting user records, as well as retrieving a list of all users.
 * @author Abi Anan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface IUserService {

    /**
     * Creates a new user based on the provided user request data.
     *
     * @param userRequest The object containing user data to be saved.
     * @return The newly created user object.
     */
    User createUser(UserRequest userRequest);

    /**
     * Retrieves a user by their unique identifier (userId).
     *
     * @param userId The unique identifier of the user.
     * @return The user with the specified userId.
     */
    UserDto getUser(UUID userId);

    /**
     * Updates a user based on the provided user data (DTO).
     *
     * @param userDto The DTO object containing updated user data.
     * @return The updated user object.
     */
    UserDto updateUser(UserDto userDto);

    /**
     * Deletes a user with the specified userId.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return A boolean indicating whether the user was successfully deleted.
     */
    boolean deleteUser(UUID userId);

    /**
     * Retrieves a list of all users in the system.
     *
     * @return A list of UserDto objects representing all users.
     */
    List<UserDto> findAllUsers();
}
