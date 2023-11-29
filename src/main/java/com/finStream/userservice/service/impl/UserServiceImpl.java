package com.finStream.userservice.service.impl;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.dto.UserRequest;
import com.finStream.userservice.entity.User;
import com.finStream.userservice.exception.UserNotFoundException;
import com.finStream.userservice.exception.UsernameAlreadyExistsException;
import com.finStream.userservice.mapper.UserMapper;
import com.finStream.userservice.repository.UserRepository;
import com.finStream.userservice.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Abi Anand <a href="mailto:abianand382@gmail.com"></a>"
 * @version 1.0.0
 */

/**
 * Service to handle user CRUD operations
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    /**
     * Creates a new user based on the provided user request.
     *
     * @param userRequest The data transfer object containing user details.
     * @return The newly created User entity.
     * @throws UsernameAlreadyExistsException if the username already exists.
     */
    @Override
    public User createUser(UserRequest userRequest) {
        if(usernameExist(userRequest.getUsername())){
            throw new UsernameAlreadyExistsException(userRequest.getUsername());
        }

        User user = userMapper.mapUserRequestToUser(userRequest);
        user.setCreatedDate(LocalDateTime.now());
//        user.setCreatedBy("SELF");
        return userRepo.save(user);
    }


    /**
     * Retrieves a user based on their unique identifier.
     * If the user is not found, a UserNotFoundException is thrown.
     *
     * @param userId The unique identifier of the user.
     * @return UserDto containing the user's information.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public UserDto getUser(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }


    /**
     * Updates an existing user's information. The method checks if the username already exists for another user,
     * and if so, throws an exception. If the user does not exist, a UserNotFoundException is thrown.
     *
     * @param userDto The data transfer object containing updated user information.
     * @return The updated UserDto.
     * @throws UsernameAlreadyExistsException if the username is taken by another user.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public UserDto updateUser(UserDto userDto) {
        Optional<User> existingUser = userRepo.findById(userDto.getId());
        if (existingUser.isPresent()) {
            boolean isSameUser = existingUser.get().getUsername().equals(userDto.getUsername());
            boolean usernameExists = usernameExist(userDto.getUsername());

            if (isSameUser || !usernameExists) {
                return convertAndSaveUser(userDto);
            } else {
                throw new UsernameAlreadyExistsException("User already exists for this username " + userDto.getUsername());
            }
        } else {
            throw new UserNotFoundException("User not found with id: " + userDto.getId());
        }
    }


    /**
     * Deletes a user by setting their 'deleted' status to true.
     * If the user is not found, a UserNotFoundException is thrown.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return True if the user is successfully marked as deleted.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public boolean deleteUser(UUID userId) {
        return userRepo.findById(userId)
                .map(user -> {
                    user.setDeleted(true);
                    userRepo.save(user);
                    return true;
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }


    /**
     * Retrieves all users who are not marked as deleted.
     *
     * @return A list of UserDto objects representing all active users.
     */
    @Override
    public List<UserDto> findAllUsers() {
        return userRepo.findAll()
                .stream()
                .filter(this::isNotDeleted)
                .map(userMapper::mapUserToUserDto)
                .collect(Collectors.toList());
    }

    /**
     * Helper method to determine if a user is not marked as deleted.
     *
     * @param user The user entity to check.
     * @return True if the user is not deleted, false otherwise.
     */
    private boolean isNotDeleted(User user){
        return !user.isDeleted();
    }


    /**
     * Checks if a username already exists in the database.
     *
     * @param username The username to check.
     * @return True if the username exists, false otherwise.
     */
    private boolean usernameExist(String username){
        return userRepo.findByUsername(username).isPresent();
    }


    /**
     * Helper method to check if a provided username is assigned to the same user.
     *
     * @param currentUserId The ID of the user to check against.
     * @param username The username to check.
     * @return True if the username is assigned to the user with the given ID, false otherwise.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    private boolean isSameUser(UUID currentUserId, String username){
        User userByUserId = userRepo.findById(currentUserId)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found with id: " + currentUserId)
                );
        return userByUserId.getUsername().equals(username);
    }


    /**
     * Converts a UserDto to a User entity, saves it in the database, and then converts it back to a UserDto.
     *
     * @param userDto The user data transfer object to convert and save.
     * @return The saved user as a UserDto.
     */
    private UserDto convertAndSaveUser(UserDto userDto) {
        User user = userRepo.save(UserMapper.INSTANCE.mapUserDtoToUser(userDto));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }


}
