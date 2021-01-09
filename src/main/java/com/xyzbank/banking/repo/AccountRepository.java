package com.xyzbank.banking.repo;

import com.xyzbank.banking.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    public Set<Accounts> findByCustId(String custId);

    public Accounts findByAccountId(String accountId);
}
