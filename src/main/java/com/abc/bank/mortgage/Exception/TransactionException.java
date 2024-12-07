package com.abc.bank.mortgage.Exception;

public class TransactionException extends RuntimeException {

    private String message;
    private String code;

    public TransactionException(String message, String message1, String code) {
        super(message);
        this.message = message1;
        this.code = code;
    }

    public TransactionException(String message, String code) {
        super(message);
        this.message = message;
        this.code = code;
    }

}
