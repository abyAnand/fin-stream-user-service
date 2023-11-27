package com.finStream.userservice.entity;


import com.finStream.userservice.VO.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.List;

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
