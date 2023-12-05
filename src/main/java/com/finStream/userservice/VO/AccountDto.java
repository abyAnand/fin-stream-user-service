package com.finStream.userservice.VO;

import com.finStream.userservice.VO.enums.AccountStatus;
import com.finStream.userservice.VO.enums.AccountType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private UUID id;
    private UUID bankId;
    private String accountNumber;
    private AccountType accountType;
    private UUID userId;
    private UUID secondaryUserId;
    private BigDecimal balance;
    //    private String currency;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    private int creditScore;
}
