package com.xyzbank.banking.service;

import com.xyzbank.banking.exception.AccountNotFoundException;
import com.xyzbank.banking.exception.CustomerNotFoundException;
import com.xyzbank.banking.model.Accounts;
import com.xyzbank.banking.model.Transaction;
import com.xyzbank.banking.repo.AccountRepository;
import com.xyzbank.banking.repo.TransactionRepository;
import com.xyzbank.banking.response.TransactionHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionDAOTest {
    @Mock
    TransactionRepository transactionRepository;

    @Mock
    AccountRepository accountRepository;

    @Autowired
    TransactionDAO transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionDAO(transactionRepository, accountRepository);
    }

    @Test
    public void shouldReturnTransactionHistory() {
        Accounts accounts = new Accounts("1000000001", "1000", "Salary Account",
                "Savings", Date.valueOf("2019-09-09"), "USD", 1000);
        when(accountRepository.findByAccountId("1000000001")).thenReturn(accounts);

        Transaction trans1 = new Transaction("10001", "Credited from ABC",
                Date.valueOf("2018-06-30"), 30000, true, accounts);
        Transaction trans2 = new Transaction("10002", "Debited for Bill",
                Date.valueOf("2019-08-28"), 1000, false, accounts);
        Transaction trans3 = new Transaction("10003", "Debited from ATM",
                Date.valueOf("2020-05-12"), 12000, false, accounts);

        Set<Transaction> transactions = new TreeSet<>();
        transactions.add(trans1);
        transactions.add(trans2);
        transactions.add(trans3);
        when(transactionRepository.findByAccount(accounts)).thenReturn(transactions);

        Set<TransactionHistory> transactionHistories = new TreeSet<>();
        transactionHistories.add(new TransactionHistory(30000, "Credited from ABC",
                "2018-06-30", true, accounts.getCurrency(), accounts.getAccountName()));
        transactionHistories.add(new TransactionHistory(1000, "Debited for Bill",
                "2019-08-28", false, accounts.getCurrency(), accounts.getAccountName()));
        transactionHistories.add(new TransactionHistory(12000, "Debited from ATM",
                "2020-05-12", false, accounts.getCurrency(), accounts.getAccountName()));

        assertEquals(transactionHistories, transactionService.transactionHistory("1000000001"));

    }

    @Test
    public void shouldThrowAccountNotFoundExceptionForInvalidAccountId(){

        when(accountRepository.findByAccountId("1000")).thenReturn(null);
        assertThrows(AccountNotFoundException.class, () -> {
            transactionService.transactionHistory("1000");
        });
        verify(accountRepository).findByAccountId("1000");
    }

}
