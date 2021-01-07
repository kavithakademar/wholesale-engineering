package com.xyzbank.banking.response;

import java.sql.Date;

public class AccountResponse implements Comparable {
    private String accountId;

    private String accountName;

    private String accountType;

    private Date balanceDate;

    private String currency;

    private int balance;

    public AccountResponse(String accountId, String accountName,
                           String accountType, Date balanceDate, String currency,
                           int balance) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountType = accountType;
        this.balanceDate = balanceDate;
        this.currency = currency;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
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
    public int compareTo(Object o) {
        AccountResponse that = (AccountResponse) o;
        return this.accountId.compareTo(that.accountId);
    }
}
