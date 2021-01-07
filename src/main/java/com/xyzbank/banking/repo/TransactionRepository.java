package com.xyzbank.banking.repo;

import com.xyzbank.banking.model.Accounts;
import com.xyzbank.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public Set<Transaction> findByAccount(Accounts account);
}
