package com.finStream.userservice.service.impl;

import com.finStream.userservice.VO.AccountDto;
import com.finStream.userservice.VO.LoanDto;
import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.entity.User;
import com.finStream.userservice.exception.UserNotFoundException;
import com.finStream.userservice.exception.UsernameAlreadyExistsException;
import com.finStream.userservice.mapper.UserMapper;
import com.finStream.userservice.repository.UserRepository;
import com.finStream.userservice.service.IUserService;
import com.finStream.userservice.service.client.AccountsFeignClient;
import com.finStream.userservice.service.client.LOAN_FEIGN_CLIENT;
import com.finStream.userservice.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    private final AccountsFeignClient accountsClient;
    private final LOAN_FEIGN_CLIENT loanClient;
    private final ImageService imageService;

    /**
     * Creates a new user based on the provided user request.
     *
     * @param userDto The data transfer object containing user details.
     * @return The newly created User entity.
     * @throws UsernameAlreadyExistsException if the username already exists.
     */
    @Override
    public User createUser(UserDto userDto) {
        if(emailExist(userDto.getEmail())){
            throw new UsernameAlreadyExistsException(userDto.getEmail());
        }

//        User user = userMapper.mapUserDtoToUser(userDto);
        User user = User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .email(userDto.getEmail())
                .build();
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
        UserDto userDto = fetchUserAndConvertToDto(userId);
        List<AccountDto> accounts = fetchAccountsForUser(userId);
        List<LoanDto> loanList = fetchLoansForUser(userId);
        userDto.setAccountList(accounts);
        userDto.setLoanList(loanList);
        return userDto;
    }

    private UserDto fetchUserAndConvertToDto(UUID userId) {
        User user = userRepo.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User not found with id: " + userId)
        );
        UserDto userDto = UserMapper.INSTANCE.mapUserToUserDto(user);
        if(user.getImageId() > 0){
            userDto.setImage(imageService.getOne(user.getImageId()));
        }
        return userDto;
    }

    private List<AccountDto> fetchAccountsForUser(UUID userId) {
        ResponseEntity<List<AccountDto>> response = accountsClient.findAccountByUserId(userId);
        return response.getBody();
    }

    private List<LoanDto> fetchLoansForUser(UUID userId) {
        ResponseEntity<List<LoanDto>> response = loanClient.getAllLoansByUserId(userId);
        return response.getBody();
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
            boolean isSameUser = existingUser.get().getEmail().equals(userDto.getEmail());
            boolean emailExist = emailExist(userDto.getEmail());

            if (isSameUser || !emailExist) {
                return convertAndSaveUser(userDto);
            } else {
                throw new UsernameAlreadyExistsException("User already exists for this email " + userDto.getEmail());
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
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    @Override
    public void deleteUser(UUID userId) {
        userRepo.findById(userId)
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
                .map(this::mapUserToDtoWithAccounts)
                .collect(Collectors.toList());
    }

    @Override
    public void blockUser(UUID userId) {
        userRepo.findById(userId)
                .map(user -> {
                    user.setBlocked(true);
                    userRepo.save(user);
                    return true;
                })
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    private UserDto mapUserToDtoWithAccounts(User user) {
        UserDto userDto = userMapper.mapUserToUserDto(user);
        List<AccountDto> accounts = fetchAccountsForUser(user.getId());
        List<LoanDto> loanList = fetchLoansForUser(user.getId());
        userDto.setAccountList(accounts);
        userDto.setLoanList(loanList);
        return userDto;
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
     * @param email The username to check.
     * @return True if the username exists, false otherwise.
     */
    private boolean emailExist(String email){
        return userRepo.findByEmail(email).isPresent();
    }


    /**
     * Helper method to check if a provided username is assigned to the same user.
     *
     * @param currentUserId The ID of the user to check against.
     * @param email The email to check.
     * @return True if the username is assigned to the user with the given ID, false otherwise.
     * @throws UserNotFoundException if the user with the specified ID is not found.
     */
    private boolean isSameUser(UUID currentUserId, String email){
        User userByUserId = userRepo.findById(currentUserId)
                .orElseThrow(
                        () -> new UserNotFoundException("User not found with id: " + currentUserId)
                );
        return userByUserId.getEmail().equals(email);
    }


    /**
     * Converts a UserDto to a User entity, saves it in the database, and then converts it back to a UserDto.
     *
     * @param userDto The user data transfer object to convert and save.
     * @return The saved user as a UserDto.
     */
    private UserDto convertAndSaveUser(UserDto userDto) {

        User user = UserMapper.INSTANCE.mapUserDtoToUser(userDto);
        if (userDto.getImage() != null) {
            user.setImageId(userDto.getImage().getId());
        }
        user = userRepo.save(user);
        UserDto savedUserDto = UserMapper.INSTANCE.mapUserToUserDto(user);
        if (userDto.getImage() != null) {
            savedUserDto.setImage(userDto.getImage());
        }
        return savedUserDto;
    }


}
