package com.finStream.userservice.entity;


import com.finStream.userservice.VO.AccountDto;
import com.finStream.userservice.enums.CollateralType;
import com.finStream.userservice.enums.EmploymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


/**
 * The User class represents user data within the application.
 * It extends the BaseEntity class, inheriting common entity fields.
 * This class includes properties such as first name, last name, username, email, and password.
 * The @Transient annotation marks the 'Accounts' property as non-persistent,
 * indicating that it won't be stored in the database.
 * Additionally, it uses Lombok annotations for generating getters, setters,
 * constructors, and a toString method, reducing boilerplate code.
 * The @Builder annotation enables the builder pattern for creating User instances.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User extends  BaseEntity{

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    @Enumerated(EnumType.STRING)
    private EmploymentStatus employmentStatus;
    private double monthlyIncome;
    private double totalAssets;
    private double totalLiabilities;
//    @Enumerated(EnumType.STRING)
//    private CollateralType collateralType;
//    private String collateralDescription;
//    private double collateralValue;
//
//    private String vehicleNumber;
//    private UUID collateralAccountId;


}
