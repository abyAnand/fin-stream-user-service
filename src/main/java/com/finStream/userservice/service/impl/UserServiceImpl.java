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

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepo;

    /**
     * @param userRequest
     * @return the newly created user object
     */
    @Override
    public User createUser(UserRequest userRequest) {
        if(usernameExist(userRequest.getUsername())){
            throw new UsernameAlreadyExistsException(userRequest.getUsername());
        }

        User user = UserMapper.INSTANCE.mapUserRequestToUser(userRequest);
        user.setCreatedBy("SELF");
        return userRepo.save(user);
    }

    /**
     * @param userId
     * @return user with the specified Id
     */
    @Override
    public UserDto getUser(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return UserMapper.INSTANCE.mapUserToUserDto(user);
    }

    /**
     * @param userId  the id of the user
     * @param userDto the Dto of the user
     * @return the updated user object
     */
    @Override
    public User updateUser(UUID userId, UserDto userDto) {
        return null;
    }

    /**
     * @param userId of the user to be deleted
     * @return a boolean indicating whether the user is deleted or not
     */
    @Override
    public boolean deleteUser(UUID userId) {
        return false;
    }

    boolean usernameExist(String username){
        return userRepo.findByUsername(username).isPresent();
    }
}
