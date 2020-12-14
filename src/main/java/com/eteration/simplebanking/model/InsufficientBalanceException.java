package com.eteration.simplebanking.model;

public class InsufficientBalanceException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException() {
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String message, Throwable cause) {
        super(message);
        initCause(cause);
    }
}