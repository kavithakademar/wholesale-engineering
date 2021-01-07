package com.xyzbank.banking.controller;

import com.xyzbank.banking.response.AccountResponse;
import com.xyzbank.banking.service.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class AccountController {

    @Autowired

    AccountDAO accountService;

    public AccountController(AccountDAO accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts")
    public ResponseEntity<Set<AccountResponse>> accounts(@RequestBody String custId) {
        System.out.println("Customer id " + custId);
        return ResponseEntity.ok(accountService.accounts(custId));
    }
}
