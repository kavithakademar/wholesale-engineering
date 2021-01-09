package com.xyzbank.banking.service;

import com.xyzbank.banking.exception.AccountNotFoundException;
import com.xyzbank.banking.entity.Accounts;
import com.xyzbank.banking.entity.Transaction;
import com.xyzbank.banking.repo.AccountRepository;
import com.xyzbank.banking.repo.TransactionRepository;
import com.xyzbank.banking.response.TransactionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class TransactionDAO {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    public TransactionDAO(TransactionRepository transactionRepository,
                          AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionDAO() {

    }

    public Set<TransactionHistory> transactionHistory(String accountId) {
        Accounts accounts = accountRepository.findByAccountId(accountId.trim());
        if (accounts == null)
            throw new AccountNotFoundException("Account not found");
        Set<Transaction> transactionSet = transactionRepository.findByAccount(accounts);
        Set<TransactionHistory> transactionHistories = new TreeSet<>();
        transactionSet.forEach(trans -> {
            String date = trans.getTransDate().toString();
            transactionHistories.add(
                    new TransactionHistory(trans.getAmount(), trans.getTransDetails(), date, trans.isCredit(),
                            trans.getAccount().getCurrency(), trans.getAccount().getAccountName()));

        });
        return transactionHistories;
    }

}
