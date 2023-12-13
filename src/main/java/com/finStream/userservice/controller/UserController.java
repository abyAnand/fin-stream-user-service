package com.finStream.userservice.controller;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.dto.UserRequest;
import com.finStream.userservice.entity.User;
import com.finStream.userservice.service.IUserService;
import com.finStream.userservice.service.client.AccountsFeignClient;
import com.finStream.userservice.service.impl.UserServiceImpl;
import feign.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@Tag(
        name = "API's fOR User Service in Fin Stream",
        description = "A REST controller for managing user-related operations"
)
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final IUserService userService;



    /**
     * Creates a new user based on the provided request data.
     *
     * @param userDto The user request data.
     * @return The created User wrapped in a ResponseEntity with status CREATED.
     */
    @Operation(
            summary = "Creates a new user",
            description = "Creates a new user based on the provided request data"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status Created"
    )
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userDto));
    }


    /**
     * Retrieves a user by their unique identifier.
     *
     * @param userId The unique identifier of the user.
     * @return The UserDto wrapped in a ResponseEntity with status OK.
     */
    @Operation(
            summary = "Retrieves a user by their ID",
            description = "Retrieves a user by their unique identifier"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK, User found"
    )
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
    @Operation(
            summary = "Updates an existing user",
            description = "Updates an existing user based on the provided user data"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK, User updated"
    )
    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }


    /**
     * Deletes a user based on their unique identifier.
     *
     * @param userId The unique identifier of the user to be deleted.
     * @return A ResponseEntity with status NO_CONTENT.
     */
    @Operation(
            summary = "Deletes a user",
            description = "Deletes a user based on their unique identifier"
    )
    @ApiResponse(
            responseCode = "204",
            description = "HTTP Status No Content, User deleted"
    )
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
    @Operation(
            summary = "Retrieves all users",
            description = "Retrieves a list of all users"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK, Users list retrieved"
    )
    @GetMapping
    public ResponseEntity<List<UserDto>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

}
