package com.xyzbank.banking.repo;

import com.xyzbank.banking.entity.Accounts;
import com.xyzbank.banking.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public Set<Transaction> findByAccount(Accounts account);
}
