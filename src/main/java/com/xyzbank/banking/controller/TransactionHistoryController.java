package com.xyzbank.banking.controller;

import com.xyzbank.banking.response.TransactionHistory;
import com.xyzbank.banking.service.Transactionervice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class TransactionHistoryController {
    @Autowired
    Transactionervice transactionService;

    @GetMapping("/accounts/transaction")
    public ResponseEntity<Set<TransactionHistory>> transactionDetails(@RequestBody String accountId) {
        return ResponseEntity.ok(transactionService.transactionHistory(accountId));
    }

}
