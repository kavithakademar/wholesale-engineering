package com.xyzbank.banking.repo;

import com.xyzbank.banking.model.Accounts;
import com.xyzbank.banking.model.Transaction;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)

public class TransactionRepositoryTest {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void shoudReturnTransactionDetails() {

        Accounts acc1 = new Accounts("1000", "1000000001");
        accountRepository.save(acc1);
        Transaction trans1 = new Transaction("10001", "Credited from ABC",
                Date.valueOf("2018-06-30"), 30000, true, acc1);
        Transaction trans2 = new Transaction("10002", "Debited for Bill",
                Date.valueOf("2019-08-28"), 1000, true, acc1);
        Transaction trans3 = new Transaction("10003", "Debited from ATM",
                Date.valueOf("2020-05-12"), 12000, false, acc1);

        Set<Transaction> transactions = new TreeSet<>();
        transactions.add(trans1);
        transactions.add(trans2);
        transactions.add(trans3);
        transactionRepository.save(trans1);
        transactionRepository.save(trans2);
        transactionRepository.save(trans3);

        acc1.addTransacaction(trans1);
        acc1.addTransacaction(trans2);
        acc1.addTransacaction(trans3);
        accountRepository.save(acc1);

        Set<Transaction> transList = transactionRepository.findByAccount(acc1);
        assertEquals(transactions, transList);

        Set<Accounts> accounts = accountRepository.findByCustId("1000");
        accounts.forEach(r -> System.out.println(r.getAccountId() + "," + r.getCustId()));
        AtomicReference<Accounts> expectedAccount = new AtomicReference<>();
        accounts.forEach(act -> {
            if (act.getAccountId().equals("1000000001"))
                expectedAccount.set(act);
        });
        assertEquals(acc1, expectedAccount.get());

    }
}

