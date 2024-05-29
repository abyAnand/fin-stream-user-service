package com.finStream.userservice.VO;


import com.finStream.userservice.VO.enums.ApprovalStatus;
import com.finStream.userservice.VO.enums.Collateral;
import com.finStream.userservice.VO.enums.LoanCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoanDto {

    private UUID id;

    private UUID bankId;

    private Bank bank;

    private String loanName;

    private UUID loanSettingId;

    private UUID userId;

    private UUID loanTypeId;

    @Enumerated(EnumType.STRING)
    private LoanCategory loanCategory;

    private double interestRate;

    private double loanAmount;

    private int loanTermMonths;

    @Enumerated(EnumType.STRING)
    private Collateral collateral;

    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    private int remainingTermMonths;

    private boolean loanClosed;

    private int missedPayments;

    private Date approvedDate;

    private AccountDto selectedAccount;

}
