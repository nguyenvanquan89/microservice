package com.quannv.loans.exception;

public class LoanAlreadyExistsException extends RuntimeException{
  public LoanAlreadyExistsException(String msg) {
    super(msg);
  }
}
