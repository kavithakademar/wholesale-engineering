package com.xyzbank.banking.service;

import com.xyzbank.banking.exception.CustomerNotFoundException;
import com.xyzbank.banking.entity.Accounts;
import com.xyzbank.banking.repo.AccountRepository;
import com.xyzbank.banking.response.AccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {
    @Mock
    AccountRepository accountRepository;
    @Autowired
    AccountService accountService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void shouldReturnAccountNumbersForValidCustomerId() {
        Set<Accounts> accounts = new HashSet<>();
        Accounts acc1 = new Accounts("1000000001", "1000", "Salary Account",
                "Savings", Date.valueOf("2019-09-09"), "USD", 1000);
        Accounts acc2 = new Accounts("1000000002", "1000", "OD Account",
                "Current", Date.valueOf("2020-09-09"), "USD", 2000);

        accounts.add(acc1);
        accounts.add(acc2);
        when(accountRepository.findByCustId("1000")).thenReturn(accounts);
        Set<AccountResponse> expectedAccounts = new TreeSet<>();
        expectedAccounts.add(new AccountResponse("1000000001", "Salary Account",
                "Savings", Date.valueOf("2019-09-09"), "USD", 1000));
        expectedAccounts.add(new AccountResponse("1000000002", "OD Account",
                "Savings", Date.valueOf("2020-09-09"), "USD", 2000));
        Set<AccountResponse> actualAccounts = accountService.accounts("1000");
        assertEquals(expectedAccounts, actualAccounts);
        verify(accountRepository).findByCustId("1000");
    }

    @Test
    public void shouldThrowCustomerNotFoundExceptionWhenNoAccountsAvailableForCustomer() {
        when(accountRepository.findByCustId("1001")).thenReturn(new TreeSet<>());
        assertThrows(CustomerNotFoundException.class, () -> {
            accountService.accounts("1001");
        });
        verify(accountRepository).findByCustId("1001");

    }
}
