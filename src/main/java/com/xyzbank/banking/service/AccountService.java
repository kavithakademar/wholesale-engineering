package com.xyzbank.banking.service;

import com.xyzbank.banking.exception.CustomerNotFoundException;
import com.xyzbank.banking.entity.Accounts;
import com.xyzbank.banking.repo.AccountRepository;
import com.xyzbank.banking.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountService() {

    }

    public Set<AccountResponse> accounts(String custId) {
        Set<AccountResponse> accountNums = new TreeSet<>();
        Set<Accounts> accounts = accountRepository.findByCustId(custId.trim());
        if (accounts == null || accounts.size() == 0)
            throw new CustomerNotFoundException("Customer not found");

        accounts.forEach(acc -> {
            accountNums.add(
                    new AccountResponse(acc.getAccountId(), acc.getAccountName(), acc.getAccountType(),
                            acc.getBalanceDate(), acc.getCurrency(), acc.getBalance()));
        });
        return accountNums;
    }

}
