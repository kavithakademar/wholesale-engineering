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
public class AccountDAO {
    @Autowired
    AccountRepository accountRepository;

    public AccountDAO(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public AccountDAO() {

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
