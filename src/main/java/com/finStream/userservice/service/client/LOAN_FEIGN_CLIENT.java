package com.finStream.userservice.service.client;

import com.finStream.userservice.VO.LoanDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("LOAN-SERVICE")
public interface LOAN_FEIGN_CLIENT {

    @GetMapping(value = "/loans/user/{userId}", consumes = "application/json")
    public ResponseEntity<List<LoanDto>> getAllLoansByUserId(@PathVariable UUID userId);
}
