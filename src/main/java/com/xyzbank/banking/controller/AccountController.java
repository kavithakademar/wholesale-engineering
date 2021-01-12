package com.xyzbank.banking.controller;

import com.xyzbank.banking.response.AccountResponse;
import com.xyzbank.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<Set<AccountResponse>> accounts(@RequestBody String custId) {
        System.out.println("Customer id " + custId);
        return ResponseEntity.ok(accountService.accounts(custId));
    }
}
