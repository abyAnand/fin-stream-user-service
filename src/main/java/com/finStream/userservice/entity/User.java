package com.finStream.userservice.entity;


import com.finStream.userservice.VO.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
public class User extends BaseEntity{


    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    @Transient
    private List<Account> Accounts;


}
