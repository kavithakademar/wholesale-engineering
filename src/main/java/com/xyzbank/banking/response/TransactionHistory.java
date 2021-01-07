package com.xyzbank.banking.response;

import java.util.Objects;

public class TransactionHistory implements Comparable {
    private int debitAmount;
    private int creditAmount;
    private String details;
    private String date;
    private String transactionType;
    private String currency;
    private String accountName;


    public TransactionHistory(int amount, String details, String date, boolean isCredit,
                              String currency, String accountName) {

        this.details = details;
        this.date = date;
        this.currency = currency;
        this.accountName = accountName;
        if (isCredit) {
            transactionType = "Credit";
            this.creditAmount = amount;
        } else {
            transactionType = "Debit";
            this.debitAmount = amount;
        }
    }

    public String getAccountName() {
        return accountName;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getDebitAmount() {
        return debitAmount;
    }

    public int getCreditAmount() {
        return creditAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDetails() {
        return details;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int compareTo(Object o) {
        TransactionHistory that = (TransactionHistory) o;
        return this.date.compareTo(that.date);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionHistory that = (TransactionHistory) o;
        return debitAmount == that.debitAmount &&
                creditAmount == that.creditAmount &&
                Objects.equals(details, that.details) &&
                Objects.equals(date, that.date) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(debitAmount, creditAmount, details, date, transactionType, currency);
    }
}
