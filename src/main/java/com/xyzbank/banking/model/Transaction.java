package com.xyzbank.banking.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Transaction implements Comparable {
    @Id
    private String transId;
    private String transDetails;
    private Date transDate;
    private int amount;
    private boolean credit;
    @ManyToOne
    @JoinColumn(name = "accountId")
    private Accounts account;

    public Transaction() {
    }

    public Transaction(String transId, String transDetails, Date transDate,
                       int amount, boolean credit, Accounts account) {
        this.transId = transId;
        this.transDetails = transDetails;
        this.transDate = transDate;
        this.amount = amount;
        this.credit = credit;
        this.account = account;
    }


    public String getTransId() {
        return transId;
    }

    public String getTransDetails() {
        return transDetails;
    }

    public Date getTransDate() {
        return transDate;
    }


    public int getAmount() {
        return amount;
    }

    public Accounts getAccount() {
        return account;
    }

    public boolean isCredit() {
        return credit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount == that.amount &&
                Objects.equals(transId, that.transId) &&
                Objects.equals(transDetails, that.transDetails) &&
                Objects.equals(transDate, that.transDate) &&
                Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transId, transDetails, transDate, amount, account);
    }

    @Override
    public int compareTo(Object o) {
        Transaction that = (Transaction) o;
        return this.getTransId().compareTo(that.getTransId());
    }
}
