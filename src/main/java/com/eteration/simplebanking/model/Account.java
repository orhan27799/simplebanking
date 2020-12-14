package com.eteration.simplebanking.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.eteration.simplebanking.common.GeneralEnumerationDefinitions;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Unique identifier for database
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * owner
     * Represent user's account owner
     */
   
    private String owner;

    /**
     * accountNumber
     * Represent user's account number
     */
   
    private String accountNumber;

    /**
     * balance
     * Balance must be BigDecimal instead of double
     * 
     */
   
    private BigDecimal balance;

    /**
     * transactions
     * Whole customer's transaction process history
     */
    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Transaction> transactions;

    /**
     * no-arg default constructor for Account model
     */
    public Account() {
    }

    /**
     * @param owner
     * @param accountNumber Trusted package constructor.
     *                      Trusted simply means when Account initialized, balance always will be 0
     *                      if balance is 0, val could not be INFLATED.
     */
    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.transactions = new ArrayList<>();
        this.balance = new BigDecimal(0);
    }

    public void post(Transaction transaction) throws InsufficientBalanceException {
        try {
            transaction.doTransaction(this);
            transaction.setTransactionStatus(GeneralEnumerationDefinitions.TransactionStatus.OK.getShortCode());
        } finally {
        	transactions.add(transaction);
        }
    }

    public void deposit(double d) {
        balance = balance.add(BigDecimal.valueOf(d));
    }

    public void withdraw(double d) throws InsufficientBalanceException {
        if (balance.subtract(BigDecimal.valueOf(d)).compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }
        balance = balance.subtract(BigDecimal.valueOf(d));

    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}