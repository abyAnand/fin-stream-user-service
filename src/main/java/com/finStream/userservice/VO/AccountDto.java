package com.finStream.userservice.VO;

import com.finStream.userservice.VO.enums.AccountStatus;
import com.finStream.userservice.VO.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private UUID id;
    private UUID bankId;
    private UUID accountSettingId;
    private UUID accountNumber;
    private String accountName;
    @Column(name = "accounts_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    private UUID userId;
    private BigDecimal balance;
    private boolean isDeleted;
    private UUID secondaryUserId;
    private BigDecimal interestRate;
    private BigDecimal overdraftLimit;
    private int cdTerm;
    private List<UUID> accountHolders;
    private int accountHoldersLimit;
    private int maxMonthlyTransactions;
    private UUID linkedSavingsAccountId;
}
