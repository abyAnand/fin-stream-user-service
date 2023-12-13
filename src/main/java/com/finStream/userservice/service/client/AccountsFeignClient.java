package com.finStream.userservice.service.client;

import com.finStream.userservice.VO.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@FeignClient("ACCOUNT-SERVICE")
public interface AccountsFeignClient {

    @GetMapping(value = "/accounts/{accountId}", consumes = "application/json")
    public ResponseEntity<AccountDto> findAccountById(@PathVariable UUID accountId);

    @GetMapping(value = "/accounts/user/{userId}", consumes = "application/json")
    public ResponseEntity<List<AccountDto>> findAccountByUserId(@PathVariable UUID userId);

    @GetMapping(value = "/accounts/user/{userId}", consumes = "application/json")
    public ResponseEntity<List<AccountDto>> findAccountByUserId(@PathVariable Integer userId);


}
