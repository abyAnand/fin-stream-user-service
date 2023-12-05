package com.finStream.userservice.dto;

import com.finStream.userservice.VO.AccountDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Data Transfer Object (DTO) for User.
 * This class is used to transfer user data within the application,
 * typically when fetching data from or sending data to the database.
 * It encapsulates user-related information like ID, name, username, email, and password.
 *
 * @Data Generates boilerplate code like getters, setters, equals, hashCode, and toString methods.
 * @AllArgsConstructor Generates a constructor with arguments for all fields in the class.
 * @NoArgsConstructor Generates a default constructor with no arguments.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private List<AccountDto> Accounts;
}
