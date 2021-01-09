package com.xyzbank.banking.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Accounts implements Comparable {
    @Id
    private String accountId;

    private String custId;

    private String accountName;

    private String accountType;

    private Date balanceDate;

    private String currency;

    private int balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Transaction> transactions = new TreeSet<>();

    public Accounts() {

    }

    public Accounts(String custId, String accountId, Set<Transaction> transactions) {
        this.custId = custId;
        this.accountId = accountId;
        this.transactions = transactions;
    }

    public Accounts(String custId, String accountId) {
        this.custId = custId;
        this.accountId = accountId;
    }

    public Accounts(String accountId, String custId, String accountName,
                    String accountType, Date balanceDate, String currency,
                    int balance) {
        this.accountId = accountId;
        this.custId = custId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balanceDate = balanceDate;
        this.currency = currency;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCustId() {
        return custId;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransacaction(Transaction transaction) {

        transactions.add(transaction);
        if (transaction.isCredit())
            this.balance = balance + transaction.getAmount();
        else
            this.balance = balance - transaction.getAmount();
        this.balanceDate = transaction.getTransDate();
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public Date getBalanceDate() {
        return balanceDate;
    }

    public String getCurrency() {
        return currency;
    }

    public int getBalance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounts accounts = (Accounts) o;
        return Objects.equals(accountId, accounts.accountId) &&
                Objects.equals(custId, accounts.custId);
    }

    @Override
    public int compareTo(Object o) {
        Accounts that = (Accounts) o;
        return this.getAccountId().compareTo(that.accountId);
    }
}