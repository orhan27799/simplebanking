package com.eteration.simplebanking.service;

import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.InsufficientBalanceException;

public interface AccountService {

	Account findById(int id);

	void debit(double value, int id) throws Exception;

	void credit(double value, int id) throws InsufficientBalanceException;

}
