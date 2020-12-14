package com.eteration.simplebanking.model;

import javax.persistence.Entity;

@Entity
public class PhoneBillPaymentTransaction extends Transaction {

    private String providerName;
    private String phoneNumber;

    public PhoneBillPaymentTransaction(){}

    public PhoneBillPaymentTransaction(String providerName, String phoneNumber, double value){
        super(value);
        this.providerName=providerName;
        this.phoneNumber=phoneNumber;
    }

    @Override
    public void doTransaction(Account account) throws InsufficientBalanceException {
        account.withdraw(super.getAmount());
    }

    public String getProviderName() {
        return providerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}