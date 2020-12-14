package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {

    public DepositTransaction(){}

    public DepositTransaction(double value){
        super(value);
    }

    @Override
    public void doTransaction(Account account) {
        account.deposit(super.getAmount());
    }
}