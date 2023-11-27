package com.finStream.userservice.controller;

import com.finStream.userservice.dto.UserDto;
import com.finStream.userservice.dto.UserRequest;
import com.finStream.userservice.entity.User;
import com.finStream.userservice.service.IUserService;
import com.finStream.userservice.service.impl.UserServiceImpl;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.UserDatabase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


/**
 * @author Abi Anand <a href="mailto:abianand382@gmail.com"></a>"
 * @version 1.0.0
 * @since 1.0.0
 */

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    /**
     * @param userRequest request object from the user
     * @return ResponseEntity with the createdUser object and HttpStatus code
     */

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(userRequest));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> findUserById(@PathVariable UUID userId){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(userService.getUser(userId));
    }

}
