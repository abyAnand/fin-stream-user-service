package com.finStream.userservice.dto;

import com.finStream.userservice.VO.AccountDto;
import com.finStream.userservice.VO.LoanDto;
import com.finStream.userservice.entity.Image;
import com.finStream.userservice.enums.CollateralType;
import com.finStream.userservice.enums.EmploymentStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    private String email;
    private Image image;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    private double monthlyIncome;
    private double totalAssets;
    private double totalLiabilities;
    private List<AccountDto> accountList;
    private List<LoanDto> loanList;
    private LocalDateTime createdDate;
    private boolean blocked;
    private boolean deleted;
}
