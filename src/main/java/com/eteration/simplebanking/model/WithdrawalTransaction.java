package com.eteration.simplebanking.model;

import javax.persistence.Entity;

import com.eteration.simplebanking.common.GeneralEnumerationDefinitions;

@Entity
public class WithdrawalTransaction extends Transaction {

    public WithdrawalTransaction(){}

    public WithdrawalTransaction(double value){
        super(value);
    }

    @Override
    public void doTransaction(Account account) throws InsufficientBalanceException {
        account.withdraw(super.getAmount());
        setTransactionStatus(GeneralEnumerationDefinitions.TransactionStatus.OK.getShortCode());

    }
}

