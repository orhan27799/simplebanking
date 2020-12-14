package com.eteration.simplebanking.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.annotation.JSONField;

@Entity
@Table(name="transaction")
public abstract class Transaction {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name="amount")
    @JSONField(name="amount")
    private double amount;

    @Column(name="create_date")
    private Date createDate;

    @Column(name="type")
    private String type;

    @Column(name="transaction_status")
    private String transactionStatus;

    public Transaction(){}

    public Transaction(double amount){
        this.id= UUID.randomUUID();
        this.amount=amount;
        this.createDate=new Date();
        this.type=this.getClass().getSimpleName();
    }

    public abstract void doTransaction(Account account) throws InsufficientBalanceException;

    

    public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}