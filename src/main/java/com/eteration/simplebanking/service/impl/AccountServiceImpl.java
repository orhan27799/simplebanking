package com.eteration.simplebanking.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eteration.simplebanking.dao.AccountRepository;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService   {

	   @Autowired
	   private AccountRepository accountRepository;

	

	    @Override
	    public Account findById(int id) {
	        Optional<Account> result = accountRepository.findById(id);
	        Account theAccount;
	        if (result.isPresent()) {
	            theAccount = result.get();
	        } else {
	            throw new RuntimeException("Did not find user id : " + id);
	        }
	        return theAccount;
	    }

	    public void debit(double value, int id) throws InsufficientBalanceException {
	        Account theAccount = findById(id);
	        DepositTransaction trx = new DepositTransaction(value);
	        theAccount.post(trx);
	        accountRepository.save(theAccount);
	    }

	    @Override
	    public void credit(double value, int id) throws InsufficientBalanceException {
	        Account theAccount = findById(id);
	        WithdrawalTransaction trx = new WithdrawalTransaction(value);
	        theAccount.post(trx);
	        accountRepository.save(theAccount);

	    }

}
