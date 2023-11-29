package com.finStream.userservice.controller;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.dto.UserRequest;
import com.finStream.userservice.entity.User;
import com.finStream.userservice.service.IUserService;
import com.finStream.userservice.service.impl.UserServiceImpl;
import feign.Response;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


/**
 * REST controller for managing user-related operations.
 * This controller provides endpoints for creating, retrieving, updating, and deleting users.
 *
 * @author Abi Anand
 * @version 1.0.0
 * @since 1.0.0
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;


    /**
     * Creates a new user based on the provided request data.
     *
     * @param userRequest The user request data.
     * @return The created User wrapped in a ResponseEntity with status CREATED.
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userRequest));
    }


    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId The unique identifier of the user.
     * @return The UserDto wrapped in a ResponseEntity with status OK.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }


    /**
     * Updates an existing user based on the provided user data.
     *
     * @param userDto The updated user data.
     * @return The updated UserDto wrapped in a ResponseEntity with status OK.
     */
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }


    /**
     * Deletes a user based on their unique identifier.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return A ResponseEntity with status NO_CONTENT.
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


    /**
     * Retrieves a list of all users.
     *
     * @return A list of UserDto wrapped in a ResponseEntity with status OK.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
