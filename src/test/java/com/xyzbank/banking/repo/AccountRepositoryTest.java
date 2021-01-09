package com.xyzbank.banking.repo;

import com.xyzbank.banking.entity.Accounts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)

public class AccountRepositoryTest {
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void shoudReturnAccountDetails() {
        Accounts acc1 = new Accounts("1000", "1000000001");
        Accounts acc2 = new Accounts("1000", "1000000002");
        Accounts acc3 = new Accounts("1001", "1000000003");
        Accounts acc4 = new Accounts("1001", "1000000004");
        accountRepository.save(acc1);
        accountRepository.save(acc3);
        accountRepository.save(acc2);
        accountRepository.save(acc4);
        Set<Accounts> actualAccount = accountRepository.findByCustId("1001");
        TreeSet<Accounts> actualSorted = new TreeSet<>(actualAccount);
        Set<Accounts> expectedAccounts = new TreeSet<>();
        expectedAccounts.add(acc3);
        expectedAccounts.add(acc4);
        assertEquals(expectedAccounts, actualSorted);
    }
}
